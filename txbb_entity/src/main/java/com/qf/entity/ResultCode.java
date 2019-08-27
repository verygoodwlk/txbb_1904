package com.qf.entity;

/**
 * 返回码的常量接口
 *
 * @version 1.0
 * @user ken
 * @date 2019/8/27 11:29
 */
public interface ResultCode {

    //请求成功
    String SUCC_CODE = "0000";

    //用户相关的返回码
    String USER_HAVE = "1001";//用户名已经存在

    //服务器异常
    String SERVER_ERROR = "5000";//服务器异常
}
