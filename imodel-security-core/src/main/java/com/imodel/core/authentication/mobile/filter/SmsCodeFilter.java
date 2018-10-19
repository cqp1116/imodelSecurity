package com.imodel.core.authentication.mobile.filter;

import com.imodel.core.properties.SecurityProperties;
import com.imodel.core.validate.exception.ValidateCodeException;
import com.imodel.core.validate.pojo.ImageCode;
import com.imodel.core.validate.pojo.ValidateCode;
import com.imodel.core.validate.processor.ValidateCodeProcess;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @auther 陈庆朋
 * @create 2018/8/20
 */
public class SmsCodeFilter extends OncePerRequestFilter implements InitializingBean {
    //加载错误处理
    private AuthenticationFailureHandler authenticationFailureHandler;
    /**
     * 加载本地session缓存
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    private Set<String> urls = new HashSet<>();
    //加载配置的url地址信息
    private SecurityProperties securityProperties;
    //加载spring的比较对象
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    /**
     * 在配置文件加载之后调用
     * @throws ServletException
     */
    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        String[] configUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getValidate().getSms().getUrl(),",");
        if(ArrayUtils.isNotEmpty(configUrls)){
            for(String configUrl : configUrls){
                urls.add(configUrl);
            }
        }
        urls.add("/authentication/mobile");
    }

    //至拦截一次
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        //验证所有的配置的url是否和请求的相互匹配
        boolean valiFlag = false;
        for(String url : urls){
            if(pathMatcher.match(url,request.getRequestURI())){
                valiFlag = true;
            }
        }
        //当请求的监听为登陆时才走这个地方
        if(valiFlag){
            try {
                this.validate(new ServletWebRequest(request));
            }catch (ValidateCodeException e){
                //权限错误验证
                authenticationFailureHandler.onAuthenticationFailure(request,response,e);
                return;
            }
        }
        chain.doFilter(request,response);
    }


    /**
     * 进行验证码的验证
     * @param request
     * @throws ServletRequestBindingException
     */
    private void validate(ServletWebRequest request) throws ServletRequestBindingException {

        ValidateCode codeInSession = (ValidateCode) sessionStrategy.getAttribute(request,
                ValidateCodeProcess.SESSION_KEY_PREFY+"SMS");

        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "smsCode");

        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException("验证码的值不能为空");
        }

        if(codeInSession == null){
            throw new ValidateCodeException("验证码不存在");
        }

        if(codeInSession.isExpried()){
            sessionStrategy.removeAttribute(request, ValidateCodeProcess.SESSION_KEY_PREFY+"SMS");
            throw new ValidateCodeException("验证码已过期");
        }

        if(!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException("验证码不匹配");
        }

        sessionStrategy.removeAttribute(request, ValidateCodeProcess.SESSION_KEY_PREFY+"SMS");
    }

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    public SessionStrategy getSessionStrategy() {
        return sessionStrategy;
    }

    public Set<String> getUrls() {
        return urls;
    }

    public void setUrls(Set<String> urls) {
        this.urls = urls;
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    public AntPathMatcher getPathMatcher() {
        return pathMatcher;
    }

    public void setPathMatcher(AntPathMatcher pathMatcher) {
        this.pathMatcher = pathMatcher;
    }

    public void setSessionStrategy(SessionStrategy sessionStrategy) {
        this.sessionStrategy = sessionStrategy;
    }
}
