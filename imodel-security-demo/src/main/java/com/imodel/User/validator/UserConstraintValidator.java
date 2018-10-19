package com.imodel.User.validator;

import com.imodel.User.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserConstraintValidator implements ConstraintValidator<UserConstraint,Object> {

    @Autowired
    private UserService userService;

    @Override
    public void initialize(UserConstraint annotation) {
        System.out.println("数据进行初始化");
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        userService.greeting("陈庆朋");
        System.out.println("这是性别调用的数据"+value);
        return true;
    }
}
