package com.imodel;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 整个项目的启动类
 */
@SpringBootApplication
@EnableAutoConfiguration
@RestController
@EnableSwagger2
public class DemoApplicaiton {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplicaiton.class,args);
    }

    @GetMapping("/hello")
    public String hello(){

        return "good morning";
    }
}
