package com.imodel.core.validate.pojo;

import java.time.LocalDateTime;

/**
 * @auther 陈庆朋
 * @create 2018/8/17
 */
public class ValidateCode {
    private String code;//输入信息
    private LocalDateTime exprieTime;//图片验证码有效时间

    /**
     * 构造方法
     * @param code
     * @param exprieTime
     */
    public ValidateCode(String code,LocalDateTime exprieTime){
        this.code = code;
        this.exprieTime = exprieTime;
    }

    /**
     * 构造方法
     * @param code
     * @param exprieIn
     */
    public ValidateCode(String code , int exprieIn){
        this.code = code;
        this.exprieTime = LocalDateTime.now().plusSeconds(exprieIn);
    }

    public boolean isExpried() {
        return LocalDateTime.now().isAfter(exprieTime);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExprieTime() {
        return exprieTime;
    }

    public void setExprieTime(LocalDateTime exprieTime) {
        this.exprieTime = exprieTime;
    }

}
