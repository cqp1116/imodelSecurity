package com.imodel.config;

import com.imodel.User.filter.TimeFilter;
import com.imodel.User.interceptor.ImodelAsyncInterceptor;
import com.imodel.User.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * spring得注册配置类
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    /**
     * 注册拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        //注册异步处理的拦截器
        registry.addInterceptor(new ImodelAsyncInterceptor());
        //注册处理时长的拦截器
        registry.addInterceptor(new TimeInterceptor());
    }
    /**
     * 注册请求执行时长处理
     * 注册过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean timeFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        TimeFilter filter = new TimeFilter();
        bean.setFilter(filter);

        List<String> urls = new ArrayList<>();
        urls.add("/user");//只对访问user得请求进行拦截
        bean.setUrlPatterns(urls);

        return bean;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("classpath:/resources/").setCacheControl(CacheControl
                .maxAge(10, TimeUnit.MINUTES)
                .cachePrivate());
        super.addResourceHandlers(registry);
    }
}
