package com.imodel.core.validate.processor;


import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码接口
 */
public interface ValidateCodeProcess {

    String SESSION_KEY_PREFY = "SESSION_KEY_FOR_CODE_";


    /**
     * 生成所有验证码操作
     * @param request
     * @throws Exception
     */
    void create(ServletWebRequest request) throws Exception;
}
