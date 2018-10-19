package com.imodel.core.validate.processor;

import com.imodel.core.validate.pojo.ValidateCode;
import com.imodel.core.validate.service.ValidateCodeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

/**
 * @auther 陈庆朋
 * @create 2018/8/17
 */
public abstract class AbstractValidateCodeProcess<C extends ValidateCode> implements  ValidateCodeProcess {

    /**
     * 操作session的工具类
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    /**
     * 将继承自ValidateCodeService所有实现进行引入
     */
    @Autowired
    private Map<String,ValidateCodeService> validateCodeServices;

    @Override
    public void create(ServletWebRequest request) throws  Exception {
        C validateObj = this.getValidateCode(request);
        //保存验证码
        saveSessionCode(request,validateObj);
        sendValidateCode(request,validateObj);
    }

    @SuppressWarnings("unchecked")
    private C getValidateCode(ServletWebRequest request){
        String type = this.getProcessorType(request);
        ValidateCodeService validateCodeService =validateCodeServices.get(type+"CodeService");//根据将service声明出的bean类拿到相应的service
        return (C) validateCodeService.generate(request);
    }

    /**
     * 发送验证码
     * @param request
     * @param validate
     * @throws Exception
     */
    protected  abstract  void sendValidateCode(ServletWebRequest request,C validate) throws  Exception;

    /**
     * 保存校验码
     * @param request
     * @param validateCode
     */
    private void saveSessionCode(ServletWebRequest request,C validateCode){
        sessionStrategy.setAttribute(request,SESSION_KEY_PREFY+getProcessorType(request).toUpperCase(),validateCode);
    }


    /**
     * 根据请求的url获取校验码的类型
     * @param request
     * @return
     */
    private String getProcessorType(ServletWebRequest request) {
        return StringUtils.substringAfter(request.getRequest().getRequestURI(), "/code/");
    }
}
