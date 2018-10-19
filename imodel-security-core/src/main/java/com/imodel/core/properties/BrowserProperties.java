package com.imodel.core.properties;

/**
 * @auther 陈庆朋
 * @create 2018/8/14
 */
public class BrowserProperties {
    private String loginPage = "/login.html";
    private LoginType loginType = LoginType.REDIRECT;
    private int rememberMeSeconds = 3600;//添加记住我的时间长度

    public String getLoginPage() {
        return loginPage;
    }

    public int getRememberMeSeconds() {
        return rememberMeSeconds;
    }

    public void setRememberMeSeconds(int rememberMeSeconds) {
        this.rememberMeSeconds = rememberMeSeconds;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }
}
