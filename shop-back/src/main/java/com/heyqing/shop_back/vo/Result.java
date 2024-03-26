package com.heyqing.shop_back.vo;

import com.heyqing.shop_back.dao.pojo.enums.MateEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ClassName:Result
 * Package:com.heyqing.shop_back.vo
 * Description:
 *
 * @Date:2024/1/25
 * @Author:Heyqing
 */
@Data
@AllArgsConstructor
public class Result {

    /**
     * 返回实体对象
     */
    private Object data;

    /**
     * 返回参数信息
     */
    private Mate mate;

    /**
     * 成功返回
     *
     * @param data
     * @param mate
     * @return
     */
    public static Result success(Object data,  Mate mate) {
        return new Result(data, mate);
    }

    public static Result fail(Mate mate) {
        return new Result(null, mate);
    }

}
