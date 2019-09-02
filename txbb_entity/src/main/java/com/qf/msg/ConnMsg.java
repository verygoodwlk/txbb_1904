package com.qf.msg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version 1.0
 * @user ken
 * @date 2019/9/2 11:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConnMsg extends NettyMsg{

    //用户id
    private Integer uid;
    //设备号
    private String did;
}
