package com.imodel.User.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.TimeUnit;


/**
 * 模拟长消息处理
 */
@Component
public class JMSTimeTask {

    private final Logger logger =LoggerFactory.getLogger(this.getClass());

    /**
     * 模拟消息的长链接请求
     * @param deferred
     */
    @Async
    public void execute(DeferredResult<String> deferred){
        logger.info(Thread.currentThread().getName()+"==========开始进行长链接请求");
        try{
            TimeUnit.SECONDS.sleep(1);//请求时长为1分钟
            deferred.setResult("请求完成");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
