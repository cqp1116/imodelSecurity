package com.imodel.User.pojo;

import com.imodel.User.validator.UserConstraint;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Past;
import java.util.Date;

public class UserPojo{
    private String id;
    @NotBlank(message = "用户名不是为空")
    @ApiModelProperty(value = "用户名")
    private String userName;
    @UserConstraint(message = "性别啊性别")
    @ApiModelProperty(value = "这是用户性别信息")
    private String userSex;
    private String userSchool;
    private String address;

    @Past(message = "生日必须是过去时间")
    private Date birthday;

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserSchool() {
        return userSchool;
    }

    public void setUserSchool(String userSchool) {
        this.userSchool = userSchool;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
