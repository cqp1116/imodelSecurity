package com.imodel.core.properties;

/**
 * @auther 陈庆朋
 * @create 2018/8/23
 */
public class SocialProperties {
    private String filterProcessUrl = "/auth";
    private QQProperties qq = new QQProperties();

    public String getFilterProcessUrl() {
        return filterProcessUrl;
    }

    public void setFilterProcessUrl(String filterProcessUrl) {
        this.filterProcessUrl = filterProcessUrl;
    }

    public QQProperties getQq() {
        return qq;
    }

    public void setQq(QQProperties qq) {
        this.qq = qq;
    }
}
