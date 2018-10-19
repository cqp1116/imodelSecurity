package com.imodel.core.validate.exception;


import org.springframework.security.core.AuthenticationException;

/**
 * @auther 陈庆朋
 * @create 2018/8/15
 */
public class ValidateCodeException extends AuthenticationException {

    /**
     * 实现权限控制
     * 异常中的方法
     * @param msg
     */
    public ValidateCodeException(String msg) {
        super(msg);
    }
}
