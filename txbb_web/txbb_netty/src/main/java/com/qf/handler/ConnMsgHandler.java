package com.qf.handler;

import com.qf.channel.ChannelGroup;
import com.qf.msg.ConnMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 处理客户端的连接消息
 *
 * @version 1.0
 * @user ken
 * @date 2019/9/2 11:33
 */
public class ConnMsgHandler extends SimpleChannelInboundHandler<ConnMsg> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ConnMsg connMsg) throws Exception {
        System.out.println("接收到客户端的初始化连接的消息！");

        //客户端的用户id
        Integer uid = connMsg.getUid();
        //获得设备号
        String did = connMsg.getDid();

        //保存当前的连接关系
        ChannelGroup.addChannel(did, channelHandlerContext.channel());
    }
}
