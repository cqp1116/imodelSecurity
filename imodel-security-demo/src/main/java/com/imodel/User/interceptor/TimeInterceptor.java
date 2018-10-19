package com.imodel.User.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 用拦截器得方式进行计算
 * 只能记录方法和类，无法记录内容
 */
@Component
public class TimeInterceptor implements HandlerInterceptor {

    //预加载拦截器
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
        System.out.println("预处理拦截器");
        HandlerMethod hm = (HandlerMethod)obj;
        System.out.println(hm.getBean().getClass().getName());
        System.out.println(hm.getMethod().getName());
        request.setAttribute("startTime",new Date().getTime());
        return true;
    }

    //执行加载拦截器
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView mv) throws Exception {
        System.out.println("拦截器处理");
        Long start = Long.parseLong(request.getAttribute("startTime")+"");
        System.out.println("拦截器中总共耗时"+(new Date().getTime() - start));
    }

    //处理完成拦截器,如果抛出异常一样会执行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception e) throws Exception {

    }
}
