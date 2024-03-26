package com.heyqing.shop_back.vo;

import lombok.Data;

/**
 * ClassName:Mate
 * Package:com.heyqing.shop_back.vo
 * Description:
 *
 * @Date:2024/1/25
 * @Author:Heyqing
 */
@Data
public class Mate {

    /**
     * 返回值
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String msg;

    public Mate(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
