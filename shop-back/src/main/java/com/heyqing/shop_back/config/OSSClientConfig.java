package com.heyqing.shop_back.config;

import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName:OSSClientConfig
 * Package:com.heyqing.shop_back.config
 * Description:
 *
 * @Date:2024/2/11
 * @Author:Heyqing
 */
@Configuration
public class OSSClientConfig {
    @Value("${spring.cloud.alicloud.oss.endpoint}")
    String endpoint;
    @Value("${spring.cloud.alicloud.access-key}")
    String accessKeyId;
    @Value("${spring.cloud.alicloud.secret-key}")
    String accessKeySecret;

    // 创建OSS客户端Bean
    @Bean
    public OSSClient getOSSClient() {
        return new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }
}

