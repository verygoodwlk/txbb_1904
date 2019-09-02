package com.qf.txbb_netty;

import com.qf.handler.ConnMsgHandler;
import com.qf.handler.HeartMsgHandler;
import com.qf.handler.WebSocketMsgHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @user ken
 * @date 2019/9/2 10:52
 */
@Component
public class NettyServer implements CommandLineRunner {

    @Value("${server.port}")
    private int port;

    private EventLoopGroup master = new NioEventLoopGroup();
    private EventLoopGroup slave = new NioEventLoopGroup();

    @Override
    public void run(String... args) throws Exception {
        //启动Netty服务
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap
                    .group(master, slave)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer() {
                        @Override
                        protected void initChannel(Channel channel) throws Exception {
                            ChannelPipeline pipeline = channel.pipeline();

                            //设置Channel处理器链
                            pipeline.addLast(new HttpServerCodec());
                            pipeline.addLast(new HttpObjectAggregator(1024 * 1024 * 10));
                            pipeline.addLast(new WebSocketServerProtocolHandler("/"));
                            pipeline.addLast(new WebSocketMsgHandler());
                            pipeline.addLast(new ConnMsgHandler());
                            pipeline.addLast(new HeartMsgHandler());
                        }
                    });

            ChannelFuture future = bootstrap.bind(port);
            future.sync();
            System.out.println("Netty服务器已经启动！");

            future.channel().closeFuture().sync();//阻塞当前线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally{
            master.shutdownGracefully();
            slave.shutdownGracefully();
        }

    }
}
