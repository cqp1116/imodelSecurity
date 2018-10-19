package com.imodel.core.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @auther 陈庆朋
 * @create 2018/8/23
 * 添加关于QQ登陆得配置
 */
public class QQProperties extends SocialProperties {
    private String providerId = "qq";

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
