package com.qf.txbb_netty;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @version 1.0
 * @user ken
 * @date 2019/9/2 14:38
 */
@Configuration
public class RabbitConfig {

    @Value("${server.port}")
    private int port;

    @Bean
    public FanoutExchange getFanoutExchange(){
        return new FanoutExchange("chat_exchange");
    }

    @Bean
    public Queue getQueue(){
        return new Queue("chat_" + port + "_queue");
    }

    @Bean
    public Binding getBinding(Queue getQueue, FanoutExchange getFanoutExchange){
        return BindingBuilder.bind(getQueue).to(getFanoutExchange);
    }
}
