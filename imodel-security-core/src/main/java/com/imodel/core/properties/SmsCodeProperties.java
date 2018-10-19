package com.imodel.core.properties;

/**
 * @auther 陈庆朋
 * @create 2018/8/17
 */
public class SmsCodeProperties {
    private int length = 4;//生成的code长度
    private int expireIn = 60;//过期时间
    private String url;//取消的地址

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(int expireIn) {
        this.expireIn = expireIn;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
