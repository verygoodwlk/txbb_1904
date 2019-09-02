package com.qf.handler;

import com.qf.msg.EditMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * @version 1.0
 * @user ken
 * @date 2019/9/2 15:52
 */
public class EditMsgHandler extends SimpleChannelInboundHandler<EditMsg> {

    private RabbitTemplate rabbitTemplate;

    public EditMsgHandler(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, EditMsg editMsg) throws Exception {
        //收到正在输入或者停止输入的消息
        rabbitTemplate.convertAndSend("chat_exchange", "", editMsg);

    }
}
