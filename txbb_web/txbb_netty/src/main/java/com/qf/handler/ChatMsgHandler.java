package com.qf.handler;

import com.qf.msg.ChatMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * @version 1.0
 * @user ken
 * @date 2019/9/2 16:40
 */
@AllArgsConstructor
@NoArgsConstructor
public class ChatMsgHandler extends SimpleChannelInboundHandler<ChatMsg> {

    private RabbitTemplate rabbitTemplate;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ChatMsg chatMsg) throws Exception {
        System.out.println("处理单聊消息！");
        //获得单聊消息

        //将单聊消息入库

        //将消息转发出去
        rabbitTemplate.convertAndSend("chat_exchange", "", chatMsg);
    }
}
