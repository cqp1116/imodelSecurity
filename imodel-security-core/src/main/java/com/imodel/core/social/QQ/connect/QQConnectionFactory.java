package com.imodel.core.social.QQ.connect;

import com.imodel.core.social.QQ.service.QQService;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.OAuth2ServiceProvider;

/**
 * @auther 陈庆朋
 * @create 2018/8/22
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQService> {


    /**
     * 生成QQ链接工厂类
     * @param providerId
     * @param appId
     * @param appSecret
     */
    public QQConnectionFactory(String providerId,String appId,String appSecret) {
        super(providerId, new QQServiceProvider(appId,appSecret), new QQAdapter());
    }
}
