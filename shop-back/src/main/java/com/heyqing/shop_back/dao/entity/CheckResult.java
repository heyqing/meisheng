package com.heyqing.shop_back.dao.entity;

import io.jsonwebtoken.Claims;

/**
 * ClassName:CheckResult
 * Package:com.heyqing.shop_back.dao.entity
 * Description:
 *
 * @Date:2024/2/2
 * @Author:Heyqing
 */
public class CheckResult {
    private int errCode;

    private boolean success;

    private Claims claims;

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Claims getClaims() {
        return claims;
    }

    public void setClaims(Claims claims) {
        this.claims = claims;
    }
}
