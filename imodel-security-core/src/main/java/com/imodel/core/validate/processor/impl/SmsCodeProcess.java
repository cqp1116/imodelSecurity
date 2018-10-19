package com.imodel.core.validate.processor.impl;

import com.imodel.core.validate.pojo.ValidateCode;
import com.imodel.core.validate.processor.AbstractValidateCodeProcess;
import com.imodel.core.validate.service.ISmsCodeSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @auther 陈庆朋
 * @create 2018/8/17
 */
@Component("smsCodeProcess")
public class SmsCodeProcess extends AbstractValidateCodeProcess<ValidateCode> {


    @Autowired
    private ISmsCodeSendService smsCodeSendService;
    /**
     * 发送验证码
     *
     * @param request
     * @param validate
     * @throws Exception
     */
    @Override
    protected void sendValidateCode(ServletWebRequest request, ValidateCode validate) throws Exception {
        //得到从前台传递过来的电话号码
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(),"mobile");
        smsCodeSendService.sendSms(mobile,validate.getCode());
    }
}
