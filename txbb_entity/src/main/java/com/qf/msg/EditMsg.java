package com.qf.msg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version 1.0
 * @user ken
 * @date 2019/9/2 15:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditMsg extends NettyMsg {

    //发送方的id
    private Integer fromid;
    //接收方的id号
    private Integer toid;
}
