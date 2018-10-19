package com.imodel.core.properties;

/**
 * @auther 陈庆朋
 * @create 2018/8/16
 */
public class ValidateCodeProperties {

    private  ImageCodeProperties image = new ImageCodeProperties();//引入图片配置

    private SmsCodeProperties sms = new SmsCodeProperties();//引入短信配置

    public ImageCodeProperties getImage() {
        return image;
    }

    public SmsCodeProperties getSms() {
        return sms;
    }

    public void setSms(SmsCodeProperties sms) {
        this.sms = sms;
    }

    public void setImage(ImageCodeProperties image) {
        this.image = image;
    }
}
