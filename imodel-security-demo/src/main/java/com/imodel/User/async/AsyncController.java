package com.imodel.User.async;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;

import java.util.concurrent.Callable;

@RequestMapping("/async")
@RestController
public class AsyncController {

    //引入日志处理机制
    private Logger logger = LoggerFactory.getLogger(getClass());
    private final JMSTimeTask taskService;

    @Autowired
    public AsyncController(JMSTimeTask taskService) {
        this.taskService = taskService;
    }

    /**
     * 通过callable进行异步处理
     *2018-08-09 16:24:28.356  INFO 8320 --- [p-nio-80-exec-1] c.i.demo.User.async.AsyncController      : 进入主线程进行操作
     * 2018-08-09 16:24:28.357  INFO 8320 --- [p-nio-80-exec-1] c.i.demo.User.async.AsyncController      : 完成主线程操作
     * 2018-08-09 16:24:28.365  INFO 8320 --- [      MvcAsync1] c.i.demo.User.async.AsyncController      : 开启子线程操作
     * 2018-08-09 16:24:29.365  INFO 8320 --- [      MvcAsync1] c.i.demo.User.async.AsyncController      : 结束子线程操作
     * @return
     */
    @ApiOperation(value = "通callable方式调用实现异步请求")
    @GetMapping("/calldo")
    public Callable<String> callMethod(){
        logger.info("进入主线程进行操作");
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                logger.info("开启子线程操作");
                Thread.sleep(1000);//子线程睡一秒钟
                logger.info("结束子线程操作");
                return "success";
            }
        };
        logger.info("完成主线程操作");
        return callable;
    }


    /**
     * 通过websynctask进行异步处理
     * 2018-08-09 16:39:20.164  INFO 13288 --- [p-nio-80-exec-2] c.i.demo.User.async.AsyncController      : 进入相关webasynctask进行调用异步处理
     * 2018-08-09 16:39:20.172  INFO 13288 --- [      MvcAsync1] c.i.demo.User.async.AsyncController      : 进入call方法接下来就是执行相应的service应用
     * 2018-08-09 16:39:21.173  INFO 13288 --- [      MvcAsync1] c.i.demo.User.async.AsyncController      : MvcAsync1=============返回处理结果
     * 2018-08-09 16:39:21.209  INFO 13288 --- [p-nio-80-exec-1] c.i.demo.User.async.AsyncController      : webasynctask执行异步处理事件完成
     * @return
     */
    @ApiOperation(value = "通过webasynctask调用方式实现异步处理")
    @GetMapping("/taskdo")
    public WebAsyncTask<String> taskMethod(){
        logger.info("进入相关webasynctask进行调用异步处理");

        //执行相关异步处理方式
        WebAsyncTask<String> webTask = new WebAsyncTask<String>(3000, new Callable<String>() {
            @Override
            public String call() throws Exception {
                logger.info("进入call方法接下来就是执行相应的service应用");
                Thread.sleep(1000);
                logger.info(Thread.currentThread().getName()+"=============返回处理结果");
                return "success";
            }
        });

        //异步处理事件执行完成
        webTask.onCompletion(new Runnable() {
            @Override
            public void run() {
                logger.info("webasynctask执行异步处理事件完成");
            }
        });


        //异步处理事件超时
        webTask.onTimeout(new Callable<String>() {
            @Override
            public String call() throws Exception {
                logger.info("websynctask执行异步处理事件超时");
                return "error";
            }
        });

        return webTask;
    }


    /**
     * 2018-08-09 16:57:45.740  INFO 9780 --- [p-nio-80-exec-1] c.i.demo.User.async.AsyncController      : 开始执行deferred的处理机制
     * 2018-08-09 16:57:45.740  INFO 9780 --- [p-nio-80-exec-1] com.imodel.demo.User.async.JMSTimeTask   : http-nio-80-exec-1==========开始进行长链接请求
     * 2018-08-09 16:57:46.741  INFO 9780 --- [p-nio-80-exec-1] c.i.demo.User.async.AsyncController      : 消息队列返回数据
     * 2018-08-09 16:57:46.789  INFO 9780 --- [p-nio-80-exec-1] c.i.demo.User.async.AsyncController      : http-nio-80-exec-1调用异步处理完成
     * deferred进行异步接口调用
     * @return
     */
    @GetMapping("/deferred")
    public DeferredResult<String> slowTask(){
        logger.info("开始执行deferred的处理机制");
        DeferredResult<String> deferred = new DeferredResult<>();

        taskService.execute(deferred);
        logger.info("消息队列返回数据");
        //超时的回掉信息
        deferred.onTimeout(new Runnable() {
            @Override
            public void run() {
                logger.info(Thread.currentThread().getName()+"调用异步处理超时");
                deferred.setErrorResult("请求超时");
            }
        });
        //处理完成的回调方法，无论是超时还是处理成功，都会进入这个回调方法
        deferred.onCompletion(new Runnable() {
            @Override
            public void run() {
                logger.info(Thread.currentThread().getName()+"调用异步处理完成");
            }
        });

        return deferred;
    }
}
