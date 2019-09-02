package com.qf.handler;

import com.qf.msg.HeartMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * 心跳消息处理
 * @version 1.0
 * @user ken
 * @date 2019/9/2 11:37
 */
public class HeartMsgHandler extends SimpleChannelInboundHandler<HeartMsg> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HeartMsg heartMsg) throws Exception {
//        System.out.println("接收到心跳消息！");

        //返回心跳消息
        TextWebSocketFrame textWebSocketFrame = new TextWebSocketFrame("heart");
        channelHandlerContext.writeAndFlush(textWebSocketFrame);
    }
}
