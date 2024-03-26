package com.heyqing.shop_back.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heyqing.shop_back.constant.SystemConstant;
import com.heyqing.shop_back.dao.entity.UserEntity;
import com.heyqing.shop_back.dao.mapper.UserMapper;
import com.heyqing.shop_back.dao.pojo.AdminPageListPO;
import com.heyqing.shop_back.dao.pojo.LoginPO;
import com.heyqing.shop_back.dao.pojo.enums.MateEnum;
import com.heyqing.shop_back.service.UserService;
import com.heyqing.shop_back.utils.HttpClientUtil;
import com.heyqing.shop_back.utils.JwtUtils;
import com.heyqing.shop_back.utils.properties.WeixinProperties;
import com.heyqing.shop_back.vo.Mate;
import com.heyqing.shop_back.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * ClassName:UserServiceImpl
 * Package:com.heyqing.shop_back.service.impl
 * Description:
 *
 * @Date:2024/2/2
 * @Author:Heyqing
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private WeixinProperties weixinProperties;
    @Autowired
    private HttpClientUtil httpClientUtil;


    /**
     * 用户登录
     *
     * @param loginPO
     * @return
     */
    @Override
    public Result login(LoginPO loginPO) {
        //获取openid
        String jscode2sessionUrl=weixinProperties.getJscode2sessionUrl()+"?appid="+weixinProperties.getAppid()+"&secret="+weixinProperties.getSecret()+"&js_code="+loginPO.getCode()+"&grant_type=authorization_code";
        String result = httpClientUtil.sendHttpGet(jscode2sessionUrl);
        JSONObject jsonObject = JSON.parseObject(result);
        String openid = jsonObject.get("openid").toString();
        //查找用户
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("openid",openid);
        UserEntity userEntity = userMapper.selectOne(queryWrapper);
        //判断是否存在
        if (Objects.isNull(userEntity)){
            //不存在,插入新用户
            userEntity = new UserEntity();
            userEntity.setOpenid(openid);
            userEntity.setCreateTime(new Date());
            userEntity.setLastTime(new Date());
            userEntity.setNickname(loginPO.getNickName());
            userEntity.setUserAvatarUrl(loginPO.getAvatarUrl());
            userMapper.insert(userEntity);
        }else {
            //存在
            userEntity.setLastTime(new Date());
            userEntity.setNickname(loginPO.getNickName());
            userEntity.setUserAvatarUrl(loginPO.getAvatarUrl());
            userMapper.updateById(userEntity);
        }
        String token = JwtUtils.createJWT(openid,loginPO.getNickName(), SystemConstant.JWT_TTL);
        return Result.success(token,new Mate(MateEnum.SUCCESS.getCode(), MateEnum.SUCCESS.getMsg()));
    }

    @Override
    public Result getUserList(AdminPageListPO adminPageListPO) {
        String query = adminPageListPO.getQuery().trim();
        Page<UserEntity> page = new Page<>(adminPageListPO.getPageNum(),adminPageListPO.getPageSize());
        Page<UserEntity> pageResult = userMapper.userList(page,query);
        Map<String,Object> map=new HashMap<>();
        map.put("userList",pageResult.getRecords());
        map.put("total",pageResult.getTotal());
        return Result.success(map,new Mate(MateEnum.SUCCESS.getCode(), MateEnum.SUCCESS.getMsg()));
    }

    /****************************************************private***********************************************/

}
