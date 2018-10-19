package com.imodel.core.social.QQ.connect;

import com.imodel.core.social.QQ.pojo.QQUserinfo;
import com.imodel.core.social.QQ.service.QQService;
import com.imodel.core.social.QQ.service.impl.IQQService;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * @auther 陈庆朋
 * @create 2018/8/22
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQService> {

    private String appId;
    //获取授权
    private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";
    //获取令牌
    private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";
    @Override
    public QQService getApi(String accessToken) {
        return new IQQService(accessToken,appId);
    }


    public QQServiceProvider(String appId, String appSecret){
        super(new OAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
        this.appId = appId;
    }
}
