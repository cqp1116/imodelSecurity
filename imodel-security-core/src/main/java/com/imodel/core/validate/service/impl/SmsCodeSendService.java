package com.imodel.core.validate.service.impl;

import com.imodel.core.validate.service.ISmsCodeSendService;
import org.springframework.stereotype.Service;

/**
 * @auther 陈庆朋
 * @create 2018/8/17
 */
@Service
public class SmsCodeSendService implements ISmsCodeSendService {

    /**
     * 模拟短信发送的协议
     * @param mobile
     * @param code
     */
    @Override
    public void sendSms(String mobile,String code){
        System.out.println("向手机"+mobile+"发送短信验证码"+code);
    }
}
