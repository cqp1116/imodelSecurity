package com.imodel.User.service.impl;

import com.imodel.User.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    /**
     * 返回模拟接口调用
     * @param userName
     * @return
     */
    public String greeting(String userName){
        System.out.println("这是后台实际处理数据+====="+userName);
        return "hello good afternoom"+userName;
    }
}
