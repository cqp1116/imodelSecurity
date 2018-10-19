package com.imodel.core.validate.pojo;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @auther 陈庆朋
 * @create 2018/8/15
 */
public class ImageCode extends ValidateCode{
    private BufferedImage image;//图片信息


    /**
     * 根据存在时间进行构造
     * @param image
     * @param code
     * @param exprieIn
     */
    public ImageCode(BufferedImage image,String code,int exprieIn){
        super(code,exprieIn);
        this.image = image;
    }
    /**
     * 构造方法
     * @param image
     * @param code
     * @param exprieTime
     */
    public ImageCode(BufferedImage image,String code ,LocalDateTime exprieTime){
        super(code,exprieTime);
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

}
