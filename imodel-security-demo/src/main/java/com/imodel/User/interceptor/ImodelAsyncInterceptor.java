package com.imodel.User.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 专门进行拦截异步处理的拦截器
 */
@Component
public class ImodelAsyncInterceptor implements AsyncHandlerInterceptor {


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 拦截之后重新返回数据
     * @param request
     * @param response
     * @param o
     * @throws Exception
     */
    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String resp = "my name is chenqp!";
        response.setContentLength(resp.length());
        response.getOutputStream().write(resp.getBytes());
    }

    /**
     * 预处理拦截前
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        logger.info("异步处理拦截器预处理");
        return true;
    }

    /**
     * 异步事件调用完成返回前台
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        logger.info(Thread.currentThread().getName()+ "异步处理服务调用完成，返回结果给客户端");
    }


    /**
     * 拦截器完成
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        if(null != e){
            logger.error(e.getMessage());
        }
    }
}
