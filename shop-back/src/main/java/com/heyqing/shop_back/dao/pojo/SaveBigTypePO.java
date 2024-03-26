package com.heyqing.shop_back.dao.pojo;

import lombok.Data;

/**
 * ClassName:SavaBigTypePO
 * Package:com.heyqing.shop_back.dao.pojo
 * Description:
 *
 * @Date:2024/2/7
 * @Author:Heyqing
 */
@Data
public class SaveBigTypePO {
    /**
     * 大类id
     */
    private Long sortId;
    /**
     * 大类名称
     */
    private String sortName;
    /**
     * 大类图片
     */
    private String sortAvatar;
    /**
     * 大类描述
     */
    private String remark;
}
