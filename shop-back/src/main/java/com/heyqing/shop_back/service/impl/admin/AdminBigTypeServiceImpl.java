package com.heyqing.shop_back.service.impl.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heyqing.shop_back.constant.OSSTypeConstant;
import com.heyqing.shop_back.dao.entity.SortEntity;
import com.heyqing.shop_back.dao.mapper.admin.AdminSortMapper;
import com.heyqing.shop_back.dao.mapper.SortMapper;
import com.heyqing.shop_back.dao.pojo.AdminPageListPO;
import com.heyqing.shop_back.dao.pojo.SaveBigTypePO;
import com.heyqing.shop_back.dao.pojo.enums.MateEnum;
import com.heyqing.shop_back.service.admin.AdminBigTypeService;
import com.heyqing.shop_back.utils.IdUtil;
import com.heyqing.shop_back.utils.OSSUtil;
import com.heyqing.shop_back.vo.GetSortVO;
import com.heyqing.shop_back.vo.Mate;
import com.heyqing.shop_back.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

/**
 * ClassName:AdminBigTypeServiceImpl
 * Package:com.heyqing.shop_back.service.impl
 * Description:
 *
 * @Date:2024/2/13
 * @Author:Heyqing
 */
@Service
public class AdminBigTypeServiceImpl implements AdminBigTypeService {
    @Autowired
    private SortMapper sortMapper;
    @Autowired
    private AdminSortMapper adminSortMapper;

    private final OSSUtil aliyunOSSUtil;

    @Autowired
    public AdminBigTypeServiceImpl(OSSUtil aliyunOSSUtil) {
        this.aliyunOSSUtil = aliyunOSSUtil;
    }


    /**
     * 商品列表查询
     *
     * @param adminPageListPO
     * @return
     */
    @Override
    public Result getList(AdminPageListPO adminPageListPO) {
        String query = adminPageListPO.getQuery().trim();
        Page<SortEntity> page = new Page<>(adminPageListPO.getPageNum(), adminPageListPO.getPageSize());
        Page<SortEntity> pageResult = adminSortMapper.goodsList(page, query);
        List<SortEntity> sortEntityList = pageResult.getRecords();
        List<GetSortVO> sortVOList = copyList2Sort(sortEntityList);
        Map<String, Object> map = new HashMap<>();
        map.put("bigTypeList", sortVOList);
        map.put("total", pageResult.getTotal());
        return Result.success(map, new Mate(MateEnum.SUCCESS.getCode(), MateEnum.SUCCESS.getMsg()));
    }

    /**
     * 查询大类信息
     *
     * @param sortId
     * @return
     */
    @Override
    public Result getSortById(Long sortId) {
        SortEntity sortEntity = sortMapper.selectById(sortId);
        return Result.success(sortEntity, new Mate(MateEnum.SUCCESS.getCode(), MateEnum.SUCCESS.getMsg()));
    }

    /**
     * 修改or添加
     *
     * @param saveBigTypePO
     * @return
     */
    @Override
    public Result saveBigType(SaveBigTypePO saveBigTypePO) {
        SortEntity sortEntity = new SortEntity();
        if(Objects.isNull(saveBigTypePO)){
            Result.fail(new Mate(MateEnum.FAIL.getCode(), MateEnum.FAIL.getMsg()));
        }
        sortEntity.setSortName(saveBigTypePO.getSortName());
        sortEntity.setSortAvatar(saveBigTypePO.getSortAvatar());
        sortEntity.setRemark(saveBigTypePO.getRemark());
        if (saveBigTypePO.getSortId() == null || saveBigTypePO.getSortId() == -1) {
            //添加
            Long id = IdUtil.getCurrentId();
            sortEntity.setSortId(id);
            sortMapper.insert(sortEntity);
        } else {
            //修改
            sortEntity.setSortId(saveBigTypePO.getSortId());
            sortEntity.setUpdateTime(new Date());
            sortMapper.updateById(sortEntity);
        }
        return Result.success(null, new Mate(MateEnum.SUCCESS.getCode(), MateEnum.SUCCESS.getMsg()));
    }

    @Override
    public Result deleteBigType(Long sortId) {
        if (sortId == null) {
            return Result.fail(new Mate(500, "删除失败"));
        } else {
            sortMapper.deleteById(sortId);
        }
        return Result.success(null, new Mate(MateEnum.SUCCESS.getCode(), MateEnum.SUCCESS.getMsg()));

    }

    @Override
    public Result uploadImage(MultipartFile file) {
        // 返回客户端文件系统中的原始文件名
        String fileName = file.getOriginalFilename();
        System.out.println(fileName);

        try {
            if (file != null) {
                // 判定文件名是否为 ""
                if (!"".equals(fileName.trim())) {
                    File newFile = new File(fileName);
                    FileOutputStream os = new FileOutputStream(newFile);
                    // 以字节数组的形式返回文件的内容,再输出到文件输出流中
                    os.write(file.getBytes());
                    os.close();
                    // 将接受的文件传输到给定的目标文件 file-->newFile
                    file.transferTo(newFile);
                    // 根据不同文件 类型/日期 生成不同的文件夹
                    String typePath = OSSTypeConstant.SORT_PATH;
                    String timeStamp = String.valueOf(System.currentTimeMillis());
                    fileName = timeStamp + fileName.substring(fileName.lastIndexOf("."));
                    String path;
                    if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png")) {
                        // images
                        path = "images/" + typePath + fileName;
                    } else {
                        return Result.fail(new Mate(500, "暂不支持此格式"));
                    }
                    // 上传到OSS
                    String uploadUrl = aliyunOSSUtil.upLoad(newFile, path);
                    newFile.delete();
                    if (uploadUrl != null) {
                        return Result.success(uploadUrl, null);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 查询所有大类
     *
     * @return
     */
    @Override
    public Result listAll() {
        QueryWrapper queryWrapper = new QueryWrapper();
        List<SortEntity> list = adminSortMapper.selectList(queryWrapper);
        return Result.success(list, new Mate(MateEnum.SUCCESS.getCode(), MateEnum.SUCCESS.getMsg()));
    }




    /****************************************************private***********************************************/



    /**
     * 拷贝sort
     *
     * @param sortEntity
     * @return
     */
    private GetSortVO copy(SortEntity sortEntity) {
        GetSortVO sortVO = new GetSortVO();
        sortVO.setSortId(String.valueOf(sortEntity.getSortId()));
        sortVO.setSortName(sortEntity.getSortName());
        sortVO.setSortAvatar(sortEntity.getSortAvatar());
        sortVO.setCreateTime(sortEntity.getCreateTime());
        sortVO.setUpdateTime(sortEntity.getUpdateTime());
        sortVO.setRemark(sortEntity.getRemark());
        return sortVO;
    }

    /**
     * 拷贝sort
     *
     * @param sortEntityList
     * @return
     */
    private List<GetSortVO> copyList2Sort(List<SortEntity> sortEntityList) {
        List<GetSortVO> sortVOList = new ArrayList<>();
        for (SortEntity sortEntity : sortEntityList) {
            sortVOList.add(copy(sortEntity));
        }
        return sortVOList;
    }
}
