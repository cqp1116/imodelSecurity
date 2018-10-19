package com.imodel.core.properties;

/**
 * @auther 陈庆朋
 * @create 2018/8/16
 */
public class ImageCodeProperties extends  SmsCodeProperties{

    private int width = 67;//条形码宽度
    private int height  = 23;//条形码高度

    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }

}
