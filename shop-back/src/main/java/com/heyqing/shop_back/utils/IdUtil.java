package com.heyqing.shop_back.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ClassName:IdUtil
 * Package:com.heyqing.shop_back.utils
 * Description:
 *
 * @Date:2024/2/13
 * @Author:Heyqing
 */
public class IdUtil {
    public static Long getCurrentId(){
        Date date=new Date();
        return date.getTime();
    }

}
