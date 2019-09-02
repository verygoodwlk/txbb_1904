package com.qf.handler;

import com.qf.channel.ChannelGroup;
import com.qf.msg.ConnMsg;
import com.qf.msg.ShutDownMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 处理客户端的连接消息
 *
 * @version 1.0
 * @user ken
 * @date 2019/9/2 11:33
 */
public class ConnMsgHandler extends SimpleChannelInboundHandler<ConnMsg> {

    private StringRedisTemplate redisTemplate;

    public ConnMsgHandler(StringRedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ConnMsg connMsg) throws Exception {
        System.out.println("接收到客户端的初始化连接的消息！");

        //客户端的用户id
        Integer uid = connMsg.getUid();
        //获得设备号
        String did = connMsg.getDid();

        //保存当前的连接关系
        ChannelGroup.addChannel(did, channelHandlerContext.channel());

        //判断初始化连接后，当前用户和设备是否还是正常的绑定关系
        String deviceId = redisTemplate.opsForValue().get(uid.toString());
        if(!did.equals(deviceId)){
            //说明在当前设备离线的时候，该账号在另外一个设备上线过，当前设备就主动下线
            ShutDownMsg shutDownMsg = new ShutDownMsg();
            channelHandlerContext.writeAndFlush(shutDownMsg);
        }

    }
}
