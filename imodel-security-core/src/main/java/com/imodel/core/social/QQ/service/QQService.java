package com.imodel.core.social.QQ.service;

import com.imodel.core.social.QQ.pojo.QQUserinfo;


/**
 * 适配API
 */
public interface QQService {

    /**
     * 得到用户登陆得信息
     * @return
     */
    QQUserinfo getQQUserinfo();
}
