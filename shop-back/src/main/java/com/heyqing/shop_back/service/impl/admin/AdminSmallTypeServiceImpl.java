package com.heyqing.shop_back.service.impl.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heyqing.shop_back.constant.OSSTypeConstant;
import com.heyqing.shop_back.dao.entity.CategoryEntity;
import com.heyqing.shop_back.dao.mapper.CategoryMapper;
import com.heyqing.shop_back.dao.mapper.SortMapper;
import com.heyqing.shop_back.dao.pojo.AdminPageListPO;
import com.heyqing.shop_back.dao.pojo.SaveSmallTypePO;
import com.heyqing.shop_back.dao.pojo.enums.MateEnum;
import com.heyqing.shop_back.service.admin.AdminSmallTypeService;
import com.heyqing.shop_back.utils.IdUtil;
import com.heyqing.shop_back.utils.OSSUtil;
import com.heyqing.shop_back.vo.GetCategoryVO;
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
 * ClassName:AdminSmallTypeServiceImpl
 * Package:com.heyqing.shop_back.service.impl
 * Description:
 *
 * @Date:2024/2/13
 * @Author:Heyqing
 */
@Service
public class AdminSmallTypeServiceImpl implements AdminSmallTypeService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private SortMapper sortMapper;
    private final OSSUtil aliyunOSSUtil;
    @Autowired
    public AdminSmallTypeServiceImpl(OSSUtil aliyunOSSUtil) {
        this.aliyunOSSUtil = aliyunOSSUtil;
    }
    /**
     * 获取商品小类
     *
     * @param adminPageListPO
     * @return
     */
    @Override
    public Result getSmallList(AdminPageListPO adminPageListPO) {
        String query = adminPageListPO.getQuery().trim();
        Page<CategoryEntity> page = new Page<>(adminPageListPO.getPageNum(), adminPageListPO.getPageSize());
        Page<CategoryEntity> pageResult = categoryMapper.goodsList(page, query);
        List<CategoryEntity> cateList = pageResult.getRecords();
        List<GetCategoryVO> categoryVOList = copyList2Cate(cateList);
        Map<String, Object> map = new HashMap<>();
        map.put("smallTypeList", categoryVOList);
        map.put("total", pageResult.getTotal());
        return Result.success(map, new Mate(MateEnum.SUCCESS.getCode(), MateEnum.SUCCESS.getMsg()));
    }

    /**
     * 添加or保存小类
     *
     * @param saveSmallTypePO
     * @return
     */
    @Override
    public Result saveSmallType(SaveSmallTypePO saveSmallTypePO) {
        if (Objects.isNull(saveSmallTypePO)){
            return Result.fail(new Mate(MateEnum.FAIL.getCode(), MateEnum.FAIL.getMsg()));
        }
        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setCategoryName(saveSmallTypePO.getCategoryName());
        categoryEntity.setCategoryTitle(saveSmallTypePO.getCategoryTitle());
        categoryEntity.setSortId(String.valueOf(saveSmallTypePO.getBigType().getSortId()));
        categoryEntity.setRemark(saveSmallTypePO.getRemark());

        if (saveSmallTypePO.getCategoryId() == null || saveSmallTypePO.getCategoryId() == -1) {
            //添加
            Long id = IdUtil.getCurrentId();
            categoryEntity.setCategoryId(id);
            categoryMapper.insert(categoryEntity);
        } else {
            //修改
            categoryEntity.setUpdateTime(new Date());
            categoryEntity.setCategoryAvatar(saveSmallTypePO.getCategoryAvatar());
            categoryEntity.setCategoryId(saveSmallTypePO.getCategoryId());
            categoryMapper.updateById(categoryEntity);
        }
        return Result.success(null, new Mate(MateEnum.SUCCESS.getCode(), MateEnum.SUCCESS.getMsg()));
    }


    /**
     * 商品小类更换图片
     *
     * @param file
     * @return
     */
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
                    String typePath = OSSTypeConstant.CATEGORY_PATH;
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

    @Override
    public Result deleteSmallType(Long categoryId) {
        if(categoryId == null){
            return Result.fail(new Mate(500, "删除失败"));
        }else {
            categoryMapper.deleteById(categoryId);
        }
        return Result.success(null, new Mate(MateEnum.SUCCESS.getCode(), MateEnum.SUCCESS.getMsg()));
    }

    @Override
    public Result listAll() {
        QueryWrapper queryWrapper = new QueryWrapper();
        List<CategoryEntity> list = categoryMapper.selectList(queryWrapper);
        return Result.success(list, new Mate(MateEnum.SUCCESS.getCode(), MateEnum.SUCCESS.getMsg()));
    }

    /********************************************private******************************************************/

private GetCategoryVO copy(CategoryEntity categoryEntity) {
    GetCategoryVO categoryVO = new GetCategoryVO();
    //赋值
    categoryVO.setCategoryId(String.valueOf(categoryEntity.getCategoryId()));
    categoryVO.setCategoryName(categoryEntity.getCategoryName());
    categoryVO.setCategoryTitle(categoryEntity.getCategoryTitle());
    categoryVO.setCategoryAvatar(categoryEntity.getCategoryAvatar());
    categoryVO.setCreateTime(categoryEntity.getCreateTime());
    categoryVO.setUpdateTime(categoryEntity.getUpdateTime());
    categoryVO.setRemark(categoryEntity.getRemark());
    GetSortVO sortVO = new GetSortVO();
    sortVO.setSortId(String.valueOf(categoryEntity.getSortId()));
    categoryVO.setBigType(sortVO);
    String sortName = sortMapper.selectById(categoryEntity.getSortId()).getSortName();
    categoryVO.setSortName(sortName);
    return categoryVO;
}
    /**
     * 拷贝category
     *
     * @param cateList
     * @return
     */
    private List<GetCategoryVO> copyList2Cate(List<CategoryEntity> cateList) {
        List<GetCategoryVO> categoryVOList = new ArrayList<>();
        for (CategoryEntity categoryEntity : cateList) {
            categoryVOList.add(copy(categoryEntity));
        }
        return categoryVOList;
    }
}
