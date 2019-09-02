package com.qf.listener;

import com.qf.channel.ChannelGroup;
import com.qf.msg.ChatMsg;
import com.qf.msg.EditMsg;
import com.qf.msg.ShutDownMsg;
import io.netty.channel.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @user ken
 * @date 2019/9/2 14:37
 */
@Component
@RabbitListener(queues = "chat_${server.port}_queue")
public class RabbitMqListener {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 监听指定队列
     */
    @RabbitHandler
    public void msgHandler(ShutDownMsg shutDownMsg){
        //获得需要下线的设备id
        String deviceid = shutDownMsg.getDeviceid();
        //从Map集合中找到对应的Channel
        Channel channel = ChannelGroup.getChannel(deviceid);
        if(channel != null){
            //给当前设备发送下线通知
            channel.writeAndFlush(shutDownMsg);
        }
    }

    @RabbitHandler
    public void msgHandler(EditMsg editMsg){
        //获得当前编辑状态消息
        Integer toid = editMsg.getToid();
        //通过对方的id找到设备号，通过设备号找到channel
        String deviceid = redisTemplate.opsForValue().get(toid.toString());
        if(deviceid != null){
            Channel channel = ChannelGroup.getChannel(deviceid);
            if(channel != null){
                channel.writeAndFlush(editMsg);
            }
        }
    }


    @RabbitHandler
    public void msgHandler(ChatMsg chatMsg){
        //获得当前编辑状态消息
        Integer toid = chatMsg.getToid();
        //通过对方的id找到设备号，通过设备号找到channel
        String deviceid = redisTemplate.opsForValue().get(toid.toString());
        if(deviceid != null){
            Channel channel = ChannelGroup.getChannel(deviceid);
            if(channel != null){
                channel.writeAndFlush(chatMsg);
            }
        }
    }
}
