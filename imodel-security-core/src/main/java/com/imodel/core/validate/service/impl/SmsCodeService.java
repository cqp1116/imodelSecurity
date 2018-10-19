package com.imodel.core.validate.service.impl;

import com.imodel.core.properties.SecurityProperties;
import com.imodel.core.validate.pojo.ImageCode;
import com.imodel.core.validate.pojo.ValidateCode;
import com.imodel.core.validate.service.ValidateCodeService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @auther 陈庆朋
 * @create 2018/8/17
 * 将smscodeservice加到spring的注解中去
 */
@Component("smsCodeService")
public class SmsCodeService implements ValidateCodeService {
    @Autowired
    private SecurityProperties securityProperties ;
    /**
     * 生成图片
     *
     * @param request
     * @return
     */
    @Override
    public ValidateCode generate(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(securityProperties.getValidate().getSms().getLength());
        return new ValidateCode(code, securityProperties.getValidate().getSms().getExpireIn());
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}
