package com.qf.msg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version 1.0
 * @user ken
 * @date 2019/9/2 14:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShutDownMsg extends NettyMsg {

    //下线的设备号
    private String deviceid;

    {
        //设置当前为下线消息
        setType(5);
    }
}
