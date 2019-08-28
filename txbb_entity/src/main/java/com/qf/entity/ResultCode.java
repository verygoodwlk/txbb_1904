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
    String LOGIN_ERROR = "1002";//用户登录失败
    String SEARCH_EMPTRY = "1003";//用户不存在

    //好友相关的返回码
    String HAVE_SEND = "2001";//已经发送过请求
    String HAVE_FRIEND = "2002";//已经是好友

    //服务器异常
    String SERVER_ERROR = "5000";//服务器异常
}
