package com.imodel.User.controller;

import com.imodel.User.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 引入加强版controller
 */
@ControllerAdvice
public class UserExceptionHandler {


    /**
     * 系统内部错误返回信息
     * @param exception
     * @return
     */
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,Object> handleUserNotFoundException(UserNotFoundException exception){
        Map<String,Object> map = new HashMap<>();
        map.put("id",exception.getId());
        map.put("message",exception.getId()+"======="+exception.getMessage());
        return map;
    }
}
