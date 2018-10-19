package com.imodel.core.social.QQ.connect;

import com.imodel.core.social.QQ.pojo.QQUserinfo;
import com.imodel.core.social.QQ.service.QQService;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * @auther 陈庆朋
 * @create 2018/8/22
 *QQservice 适配得API
 */
public class QQAdapter implements ApiAdapter<QQService> {
    @Override
    public boolean test(QQService qqUserinfo) {
        return true;
    }

    @Override
    public void setConnectionValues(QQService apiService, ConnectionValues values) {
        QQUserinfo userinfo = apiService.getQQUserinfo();
        values.setDisplayName(userinfo.getNickname());
        values.setImageUrl(userinfo.getFigureurl_qq_1());
        values.setProfileUrl(null);
        values.setProviderUserId(userinfo.getOpenId());
    }

    @Override
    public UserProfile fetchUserProfile(QQService qqUserinfo) {
        return null;
    }

    @Override
    public void updateStatus(QQService qqUserinfo, String s) {

    }
}
