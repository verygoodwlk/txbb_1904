package com.qf.handler;

import com.alibaba.fastjson.JSON;
import com.qf.channel.ChannelGroup;
import com.qf.msg.ConnMsg;
import com.qf.msg.HeartMsg;
import com.qf.msg.NettyMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * @version 1.0
 * @user ken
 * @date 2019/9/2 11:12
 */
public class WebSocketMsgHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("有一个客户端连接了服务器！");
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("有个客户端断开了连接！");
        ChannelGroup.remoteChannel(ctx.channel());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame textWebSocketFrame) throws Exception {
        //获得客户端发送过来的消息
        String msg = textWebSocketFrame.text();

        //消息类型的判断
        NettyMsg nettyMsg = JSON.parseObject(msg, NettyMsg.class);
        switch (nettyMsg.getType()){
            case 1:
                //连接消息
                nettyMsg = JSON.parseObject(msg, ConnMsg.class);
                break;
            case 2:
                //心跳消息
                nettyMsg = JSON.parseObject(msg, HeartMsg.class);
                break;
        }

        //将消息继续往后传递
        ctx.fireChannelRead(nettyMsg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.err.println("客户端异常关闭！");
    }
}
