package com.heyqing.shop_back.utils.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * ClassName:WeixinProperties
 * Package:com.heyqing.shop_back.utils.properties
 * Description:
 *
 * @Date:2024/2/2
 * @Author:Heyqing
 */
@Component
@ConfigurationProperties(prefix = "weixin")
@Data
public class WeixinProperties {

    private String jscode2sessionUrl; // 登录凭证校验请求地址

    private String appid; // 小程序 appId

    private String secret; // 小程序 appSecret


}