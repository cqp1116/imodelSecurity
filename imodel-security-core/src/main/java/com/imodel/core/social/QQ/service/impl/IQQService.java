package com.imodel.core.social.QQ.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imodel.core.social.QQ.pojo.QQUserinfo;
import com.imodel.core.social.QQ.service.QQService;
import org.apache.commons.lang.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

/**
 * @auther 陈庆朋
 * @create 2018/8/22
 */
public class IQQService extends AbstractOAuth2ApiBinding implements QQService {
    //获取OPENID得
    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";
    //获取用户信息
    private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    private String appId;
    private String openId;

    private ObjectMapper objectMapper = new ObjectMapper();
    /**
     * 构造方法
     * @param accessToken
     * @param appId
     */
    public IQQService(String accessToken,String appId){
        super(accessToken,TokenStrategy.ACCESS_TOKEN_PARAMETER);//调用父类信息生成token，将token放倒参数中
        this.appId = appId;//读取配置文件
        //根据父类生成token得到oppenid
        String url = String.format(URL_GET_OPENID, accessToken);
        String result = getRestTemplate().getForObject(url, String.class);
        this.openId = StringUtils.substringBetween(result, "\"openid\":\"", "\"}");//截取openid
    }
    /**
     * 得到用户登陆得信息
     *
     * @return
     */
    @Override
    public QQUserinfo getQQUserinfo() {
        String url = String.format(URL_GET_USERINFO,appId,openId);
        String result = getRestTemplate().getForObject(url,String.class);//请求获取用户信息
        QQUserinfo qqObj = null;
        try{
            qqObj = objectMapper.readValue(result, QQUserinfo.class);//利用objectmapper将字符串转成对象
            qqObj.setOpenId(openId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return qqObj;
    }
}
