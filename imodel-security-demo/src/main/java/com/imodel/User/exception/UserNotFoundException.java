package com.imodel.User.exception;

/**
 * 自定义用户不存在异常处理
 */
public class UserNotFoundException  extends  RuntimeException {


    private String id;

    public UserNotFoundException(String id){
        super("user is not exist");
        this.id = id;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}
