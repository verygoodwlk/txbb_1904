package com.qf.controller;

import com.qf.entity.ResultCode;
import com.qf.entity.ResultData;
import com.qf.entity.User;
import com.qf.msg.ShutDownMsg;
import com.qf.service.IUserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
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

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

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
    public ResultData<User> login(User user, String did){

        User userLogin = userService.login(user);
        if(userLogin != null){
            userLogin.setPassword(null);

            //说明登录成功
            //将用户和设备关系保存到redis中
            String oldDeviceId = redisTemplate.opsForValue().get(userLogin.getId().toString());
            if(oldDeviceId != null && !oldDeviceId.equals(did)){
                //将oldDeviceId挤下线
                ShutDownMsg shutDownMsg = new ShutDownMsg();
                shutDownMsg.setDeviceid(oldDeviceId);
                rabbitTemplate.convertAndSend("chat_exchange", "", shutDownMsg);
            }
            redisTemplate.opsForValue().set(userLogin.getId().toString(), did);

            return ResultData.createSuccResultData(userLogin);
        }

        return ResultData.createFailResultData(ResultCode.LOGIN_ERROR, "用户名或者密码错误！");
    }

    /**
     * 根据用户名搜索好友信息
     * @param fusername
     * @return
     */
    @RequestMapping("/queryByUserName")
    public ResultData<User> queryByUserName(String fusername){

        User user = userService.queryByUserName(fusername);
        if(user != null){
            user.setPassword(null);
            return ResultData.createSuccResultData(user);
        }

        return ResultData.createFailResultData(ResultCode.SEARCH_EMPTRY, "用户不存在！");
    }

    /**
     * 查询用户信息
     * @param uid
     * @return
     */
    @RequestMapping("/queryUser")
    public User queryUserByUid(Integer uid){
        return userService.queryByUid(uid);
    }
}
