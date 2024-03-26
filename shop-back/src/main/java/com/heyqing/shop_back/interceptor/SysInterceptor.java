package com.heyqing.shop_back.interceptor;

import com.heyqing.shop_back.utils.JwtUtils;
import com.heyqing.shop_back.utils.StringUtil;
import io.jsonwebtoken.Claims;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName:SysInterceptor
 * Package:com.heyqing.shop_back.interceptor
 * Description:
 *
 * @Date:2024/2/3
 * @Author:Heyqing
 */
public class SysInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path=request.getRequestURI();
        System.out.println(path);
        if(handler instanceof HandlerMethod){
            String token = request.getHeader("token");
            if(StringUtil.isEmpty(token)){
                throw new RuntimeException("签名验证不存在");
            }else{
                Claims claims = JwtUtils.validateJWT(token).getClaims();
                // 管理员 admin开头
                if(path.startsWith("/admin")){
                    if(claims==null||!claims.getSubject().equals("admin")||!claims.getId().equals("-1")){
                        throw new RuntimeException("鉴权失败！");
                    }else{
                        System.out.println("验证成功");
                        return true;
                    }
                }else{ // 普通用户 鉴权
                    if(claims==null){
                        throw new RuntimeException("鉴权失败！");
                    }else{
                        System.out.println("验证成功");
                        return true;
                    }
                }

            }
        }else{
            return true;
        }
    }
}