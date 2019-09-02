package com.qf.txbb_user;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @version 1.0
 * @user ken
 * @date 2019/9/2 14:32
 */
@Configuration
public class RabbitConfig {

    @Bean
    public FanoutExchange getFanoutExchange(){
        return new FanoutExchange("chat_exchange");
    }
}
