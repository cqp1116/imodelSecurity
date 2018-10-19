package com.imodel.core.validate.controller;

import com.imodel.core.properties.SecurityProperties;
import com.imodel.core.validate.pojo.ImageCode;
import com.imodel.core.validate.processor.ValidateCodeProcess;
import com.imodel.core.validate.service.ValidateCodeService;
import com.imodel.core.validate.service.impl.ImageCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

/**
 * @auther 陈庆朋
 * @create 2018/8/15
 */
@RestController
@RequestMapping("/code")
public class ValidateCodeController {

    @Autowired
    private Map<String,ValidateCodeProcess> validateCodeProcess;


    /**
     * 请求信息
     * @param request
     * @param response
     * @param type
     * @throws Exception
     */
    @GetMapping("/{type}")
    public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type) throws  Exception{
        validateCodeProcess.get(type+"CodeProcess").create(new ServletWebRequest(request,response));
    }

}
