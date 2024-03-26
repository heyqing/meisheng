package com.heyqing.shop_back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.heyqing.shop_back.dao.entity.CategoryEntity;
import com.heyqing.shop_back.dao.mapper.CategoryMapper;
import com.heyqing.shop_back.dao.pojo.GetCategoryPO;
import com.heyqing.shop_back.dao.pojo.enums.MateEnum;
import com.heyqing.shop_back.service.CategoryService;
import com.heyqing.shop_back.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * ClassName:CategoryServiceImpl
 * Package:com.heyqing.shop_back.service.impl
 * Description:
 *
 * @Date:2024/1/29
 * @Author:Heyqing
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Result getCategory(GetCategoryPO getCategoryPO) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("sort_id", getCategoryPO.getSortId());
        List<CategoryEntity> categoryList = categoryMapper.selectList(queryWrapper);
        List<GetCategoryVO> categoryVOList = copyList2Category(categoryList);
        List<GetCategoryListVO> getCategoryListVO = getCategoryVOListByTitle(categoryVOList);
        return Result.success(getCategoryListVO, new Mate(MateEnum.SUCCESS.getCode(), MateEnum.SUCCESS.getMsg()));
    }


    /****************************************************private***********************************************/

    /**
     * 转化为 title-list形式
     *
     * @param categoryVOList
     * @return
     */
    private List<GetCategoryListVO> getCategoryVOListByTitle(List<GetCategoryVO> categoryVOList) {
        Set<String> AllTitle = categoryVOList.stream()
                .map(GetCategoryVO::getCategoryTitle)
                .collect(Collectors.toSet());
        List<GetCategoryListVO> getCategoryListVOList = new ArrayList<>();
        for (String title : AllTitle) {
            GetCategoryListVO getCategoryListVO = new GetCategoryListVO();
            getCategoryListVO.setCategoryTitle(title);
            getCategoryListVO.setGetCategoryVOList(findCategoryByTitle(title,categoryVOList));
            getCategoryListVOList.add(getCategoryListVO);
        }
        return getCategoryListVOList;
    }

    private List<GetCategoryVO> findCategoryByTitle(String title, List<GetCategoryVO> categoryVOList) {
        List<GetCategoryVO> getCategoryVOList = new ArrayList<>();
        for (GetCategoryVO getCategoryVO : categoryVOList) {
            if (StringUtils.equals(getCategoryVO.getCategoryTitle(),title)){
                getCategoryVOList.add(getCategoryVO);
            }
        }
        return getCategoryVOList;
    }

    /**
     * 拷贝category
     *
     * @param categoryEntity
     * @return
     */
    private GetCategoryVO copy(CategoryEntity categoryEntity) {
        GetCategoryVO getCategoryVO = new GetCategoryVO();
        getCategoryVO.setCategoryId(String.valueOf(categoryEntity.getCategoryId()));
        getCategoryVO.setCategoryAvatar(categoryEntity.getCategoryAvatar());
        getCategoryVO.setCategoryName(categoryEntity.getCategoryName());
        getCategoryVO.setCategoryTitle(categoryEntity.getCategoryTitle());
        BeanUtils.copyProperties(categoryEntity, getCategoryVO);
        return getCategoryVO;
    }

    /**
     * 拷贝category
     *
     * @param categoryList
     * @return
     */
    private List<GetCategoryVO> copyList2Category(List<CategoryEntity> categoryList) {
        List<GetCategoryVO> categoryVOList = new ArrayList<>();
        for (CategoryEntity categoryEntity : categoryList) {
            categoryVOList.add(copy(categoryEntity));
        }
        return categoryVOList;
    }
}
