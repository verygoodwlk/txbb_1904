package com.qf.handler;

import com.alibaba.fastjson.JSON;
import com.qf.msg.NettyMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * 处理出站的Handler
 *
 * @version 1.0
 * @user ken
 * @date 2019/9/2 14:48
 */
public class MsgOutHandler extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {

        if(msg instanceof NettyMsg){
            String json = JSON.toJSONString(msg);
            TextWebSocketFrame textWebSocketFrame = new TextWebSocketFrame(json);
            msg = textWebSocketFrame;
        }

        super.write(ctx, msg, promise);
    }
}
