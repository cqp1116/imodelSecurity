package com.imodel.core.validate.service;

import com.imodel.core.validate.pojo.ImageCode;
import com.imodel.core.validate.pojo.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

/**
 *
 */
public interface ValidateCodeService {


    /**
     * 生成图片
     * @param request
     * @return
     */
    ValidateCode generate(ServletWebRequest request);
}
