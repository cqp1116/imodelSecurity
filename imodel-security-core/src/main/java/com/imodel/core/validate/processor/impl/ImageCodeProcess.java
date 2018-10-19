package com.imodel.core.validate.processor.impl;

import com.imodel.core.validate.pojo.ImageCode;
import com.imodel.core.validate.pojo.ValidateCode;
import com.imodel.core.validate.processor.AbstractValidateCodeProcess;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;

/**
 * @auther 陈庆朋
 * @create 2018/8/17
 */
@Component("imageCodeProcess")
public class ImageCodeProcess extends AbstractValidateCodeProcess<ImageCode> {
    /**
     * 发送验证码
     *
     * @param request
     * @param image
     * @throws Exception
     */
    @Override
    protected void sendValidateCode(ServletWebRequest request, ImageCode image) throws Exception {
        ImageIO.write(image.getImage(),"JPEG",request.getResponse().getOutputStream());
    }
}
