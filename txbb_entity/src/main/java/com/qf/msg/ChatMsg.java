package com.qf.msg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 聊天的消息
 *
 * @version 1.0
 * @user ken
 * @date 2019/9/2 16:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMsg extends NettyMsg {

    private Integer fromid;
    private Integer toid;
    private Integer msgType;
    private String content;
    private Date createTime = new Date();
    private Integer status = 0;//0-未读 1-已读
}
