package com.imodel.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @auther 陈庆朋
 * @create 2018/8/14
 */
@ConfigurationProperties(prefix = "imodel.security")
public class SecurityProperties {
    //引入页面共用配置
    private BrowserProperties browser = new BrowserProperties();
    //引入所有的验证配置
    private ValidateCodeProperties validate = new ValidateCodeProperties();
    //引入第三方登陆验证配置
    private SocialProperties social = new SocialProperties();

    public SocialProperties getSocial() {
        return social;
    }

    public void setSocial(SocialProperties social) {
        this.social = social;
    }

    public BrowserProperties getBrowser() {
        return browser;
    }

    public ValidateCodeProperties getValidate() {
        return validate;
    }

    public void setValidate(ValidateCodeProperties validate) {
        this.validate = validate;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }
}
