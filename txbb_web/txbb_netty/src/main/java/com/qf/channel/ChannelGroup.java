package com.qf.channel;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * channel的管理对象
 *
 * did - Channel
 *
 * @version 1.0
 * @user ken
 * @date 2019/9/2 11:48
 */
//@Component
public class ChannelGroup {

    /**
     * Channel管理的集合
     */
    private static Map<String, Channel> channelMap = new ConcurrentHashMap<>();

    /**
     * 新增一个channel对象
     */
    public static Channel addChannel(String deviceid, Channel channel){
        return channelMap.put(deviceid, channel);
    }

    /**
     * 根据设备号，移除channel关系
     * @param deviceid
     * @return
     */
    public static Channel removetChannel(String deviceid){
        return channelMap.remove(deviceid);
    }

    /**
     * 根据设备号，获得对应的Channel对象
     * @param deviceid
     * @return
     */
    public static Channel getChannel(String deviceid){
        return channelMap.get(deviceid);
    }

    /**
     * 根据channel移除绑定关系
     * @param channel
     */
    public static void remoteChannel(Channel channel){
        if(channelMap.containsValue(channel)){
            Set<Map.Entry<String, Channel>> entries = channelMap.entrySet();
            for(Map.Entry<String, Channel> entry : entries){
                if(entry.getValue() == channel){
                    channelMap.remove(entry.getKey());
                    break;
                }
            }
        }
    }
}
