package com.heyqing.shop_back.dao.pojo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ClassName:MateEnum
 * Package:com.heyqing.shop_back.vo
 * Description:
 *
 * @Date:2024/1/25
 * @Author:Heyqing
 */
@AllArgsConstructor
@Getter
public enum MateEnum {

    /**
     * 成功返回
     */
    SUCCESS("获取成功", 200),

    /**
     * 返回失败
     */
    FAIL("获取失败", 400),

    /**
     * 用户无权限
     */
    NO_ACCESS("无权限",500),

    /**
     * 参数有误
     */
    PARAM_WRONG("参数有误", 1001);
    private String msg;
    private Integer code;
}
