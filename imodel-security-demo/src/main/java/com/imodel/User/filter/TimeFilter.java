package com.imodel.User.filter;


import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

/**
 * 声明过滤器
 * 现在情况过滤器会对
 * 所有请求进行过滤执行
 */
//@Component
public class TimeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("开始初始化过滤器");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("开始执行过滤器");
        long start = new Date().getTime();
        chain.doFilter(request,response);
        System.out.println("执行完成共耗时"+(new Date().getTime() - start));
    }

    @Override
    public void destroy() {
        System.out.println("过滤器销毁");
    }
}
