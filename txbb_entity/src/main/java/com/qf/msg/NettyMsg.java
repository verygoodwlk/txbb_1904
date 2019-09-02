package com.qf.msg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 父类
 *
 * @version 1.0
 * @user ken
 * @date 2019/9/2 11:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NettyMsg implements Serializable {


    /**
     * 1 - 初始化连接消息
     * 2 - 心跳消息
     * 3 - 单聊消息
     * 4 - 群聊消息
     * 5 - 强制下线
     * 6 - 正在输入中....
     * 7 - 停止输入中....
     */
    protected Integer type;//消息类型

}
