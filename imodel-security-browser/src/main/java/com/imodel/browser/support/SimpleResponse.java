package com.imodel.browser.support;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

/**
 * @auther 陈庆朋
 * @create 2018/8/14
 */
public class SimpleResponse {

    private Object content;

    public SimpleResponse(Object content){
        this.content = content;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
