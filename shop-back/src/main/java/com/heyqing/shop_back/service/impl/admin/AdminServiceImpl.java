package com.heyqing.shop_back.service.impl.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heyqing.shop_back.constant.SystemConstant;
import com.heyqing.shop_back.dao.entity.admin.AdminEntity;
import com.heyqing.shop_back.dao.mapper.admin.AdminMapper;
import com.heyqing.shop_back.dao.pojo.*;
import com.heyqing.shop_back.dao.pojo.enums.MateEnum;
import com.heyqing.shop_back.service.admin.AdminService;
import com.heyqing.shop_back.utils.IdUtil;
import com.heyqing.shop_back.utils.JwtUtils;
import com.heyqing.shop_back.utils.StringUtil;
import com.heyqing.shop_back.vo.AdminVO;
import com.heyqing.shop_back.vo.Mate;
import com.heyqing.shop_back.vo.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * ClassName:AdminServiceImpl
 * Package:com.heyqing.shop_back.service.impl
 * Description:
 *
 * @Date:2024/2/4
 * @Author:Heyqing
 */
@Service
public class AdminServiceImpl implements AdminService {

    private static final String ADMIN_NAME = "MeiShengAdmin";
    @Autowired
    private AdminMapper adminMapper;

    /**
     * 登录
     *
     * @param adminLoginPO
     * @return
     */
    @Override
    public Result login(AdminLoginPO adminLoginPO) {
        if (adminLoginPO == null) {
            return Result.fail(null);
        }
        if (StringUtil.isEmpty(adminLoginPO.getUserName())) {
            return Result.fail(new Mate(500, "用户名不能为空！"));
        }
        if (StringUtil.isEmpty(adminLoginPO.getPassword())) {
            return Result.fail(new Mate(500, "密码不能为空！"));
        }
        AdminEntity resultAdmin = adminMapper.selectOne(new QueryWrapper<AdminEntity>().eq("admin_name", adminLoginPO.getUserName()));
        if (resultAdmin == null) {
            return Result.fail(new Mate(500, "用户名不存在！"));
        }
        if (!resultAdmin.getAdminPassword().trim().equals(adminLoginPO.getPassword())) {
            return Result.fail(new Mate(500, "用户名或密码错误！"));
        }
        String token = JwtUtils.createJWT("-1", "admin", SystemConstant.JWT_TTL);
        return Result.success(token, new Mate(MateEnum.SUCCESS.getCode(), MateEnum.SUCCESS.getMsg()));
    }

    /**
     * 修改密码
     *
     * @param modifyPasswordPO
     * @return
     */
    @Override
    public Result modifyPassword(ModifyPasswordPO modifyPasswordPO) {
        if (StringUtil.isEmpty(modifyPasswordPO.getUserName())) {
            return Result.fail(new Mate(500, "用户名不能为空！"));
        }
        if (StringUtil.isEmpty(modifyPasswordPO.getNewPassword())) {
            return Result.fail(new Mate(500, "新密码不能为空！"));
        }
        AdminEntity adminEntity = copy2UserEntity(modifyPasswordPO);
        QueryWrapper<AdminEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("admin_name", modifyPasswordPO.getUserName());
        adminMapper.update(adminEntity, queryWrapper);
        return Result.success(null, new Mate(MateEnum.SUCCESS.getCode(), MateEnum.SUCCESS.getMsg()));
    }

    /**
     * 管理员身份验证
     *
     * @param addAdminPO
     * @return
     */
    @Override
    public Result adminAuth(AuthAdminPO addAdminPO) {
        System.out.println(addAdminPO);
        if (StringUtils.equals(addAdminPO.getUserName(), ADMIN_NAME)) {
            QueryWrapper<AdminEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("admin_name", ADMIN_NAME);
            AdminEntity adminEntity = adminMapper.selectOne(queryWrapper);
            if (StringUtils.equals(adminEntity.getAdminPassword(), addAdminPO.getPassword())) {
                return Result.success(null, new Mate(MateEnum.SUCCESS.getCode(), MateEnum.SUCCESS.getMsg()));
            } else {
                return Result.fail(new Mate(500, "用户名或密码错误"));
            }
        } else {
            return Result.fail(new Mate(500, "暂无权限"));
        }
    }

    /**
     * 获取admin
     *
     * @param adminPageListPO
     * @return
     */
    @Override
    public Result getList(AdminPageListPO adminPageListPO) {
        String query = adminPageListPO.getQuery().trim();
        Page<AdminEntity> page = new Page<>(adminPageListPO.getPageNum(), adminPageListPO.getPageSize());
        Page<AdminEntity> pageResult = adminMapper.adminList(page, query);
        List<AdminEntity> adminEntityList = pageResult.getRecords();
        List<AdminVO> adminVOList = copyList2Admin(adminEntityList);
        Map<String, Object> map = new HashMap<>();
        map.put("adminList", adminVOList);
        map.put("total", pageResult.getTotal() - 1);
        return Result.success(map, new Mate(MateEnum.SUCCESS.getCode(), MateEnum.SUCCESS.getMsg()));
    }

    /**
     * 添加admin
     *
     * @param addAdminPO
     * @return
     */
    @Override
    public Result addAdmin(AddAdminPO addAdminPO) {
        try {
            AdminEntity adminEntity = new AdminEntity();
            adminEntity.setAdminId(IdUtil.getCurrentId());
            adminEntity.setAdminName(addAdminPO.getAdminName());
            adminEntity.setAdminPassword(addAdminPO.getAdminPassword());
            adminEntity.setCreateTime(new Date());
            adminMapper.insert(adminEntity);
            return Result.success(null, new Mate(MateEnum.SUCCESS.getCode(), MateEnum.SUCCESS.getMsg()));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(new Mate(MateEnum.FAIL.getCode(), MateEnum.FAIL.getMsg()));
        }

    }

    /**
     * 删除admin
     *
     * @param adminId
     * @return
     */
    @Override
    public Result deleteAdmin(Long adminId) {
        try {
            if (adminId != null && adminId != -1L) {
                adminMapper.deleteById(adminId);
                return Result.success(null, new Mate(MateEnum.SUCCESS.getCode(), MateEnum.SUCCESS.getMsg()));
            } else {
                return Result.fail(new Mate(MateEnum.FAIL.getCode(), MateEnum.FAIL.getMsg()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /****************************************************private******************************************/

    /**
     * 拷贝admin
     *
     * @param adminEntity
     * @return
     */
    private AdminVO copy(AdminEntity adminEntity) {
        AdminVO adminVO = new AdminVO();
        adminVO.setAdminId(String.valueOf(adminEntity.getAdminId()));
        adminVO.setAdminName(adminEntity.getAdminName());
        adminVO.setAdminPassword(adminEntity.getAdminPassword());
        adminVO.setCreateTime(adminEntity.getCreateTime());
        return adminVO;
    }

    /**
     * 拷贝admin
     *
     * @param modifyPasswordPO
     * @return
     */
    private AdminEntity copy2UserEntity(ModifyPasswordPO modifyPasswordPO) {
        AdminEntity adminEntity = new AdminEntity();
        adminEntity.setAdminPassword(modifyPasswordPO.getNewPassword());
        return adminEntity;
    }

    /**
     * 拷贝admin
     *
     * @param adminEntityList
     * @return
     */
    private List<AdminVO> copyList2Admin(List<AdminEntity> adminEntityList) {
        List<AdminVO> adminVOList = new ArrayList<>();
        for (AdminEntity adminEntity : adminEntityList) {
            if (adminEntity.getAdminName().equals(ADMIN_NAME)) continue;
            adminVOList.add(copy(adminEntity));
        }
        return adminVOList;
    }
}
