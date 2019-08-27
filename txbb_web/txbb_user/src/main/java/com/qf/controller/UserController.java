package com.qf.controller;

import com.qf.entity.ResultCode;
import com.qf.entity.ResultData;
import com.qf.entity.User;
import com.qf.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @user ken
 * @date 2019/8/27 10:43
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 用户注册
     * @return
     */
    @RequestMapping("/register")
    public ResultData<String> register(User user){

        //注册用户
        Integer result = userService.registerUser(user);

        if(result == -1){
            //用户名已经存在
            return ResultData.createFailResultData(ResultCode.USER_HAVE, "用户名已经存在！");
        } else if(result == 1){
            //成功
            return ResultData.createSuccResultData("succ");
        }

        return ResultData.createFailResultData(ResultCode.SERVER_ERROR, "服务器异常，请扫后再试！");
    }

    /**
     * 用户登录
     * @param user
     * @return
     */
    @RequestMapping("/login")
    public ResultData<User> login(User user){

        User userLogin = userService.login(user);
        if(userLogin != null){
            userLogin.setPassword(null);
            return ResultData.createSuccResultData(userLogin);
        }

        return ResultData.createFailResultData(ResultCode.LOGIN_ERROR, "用户名或者密码错误！");
    }
}
