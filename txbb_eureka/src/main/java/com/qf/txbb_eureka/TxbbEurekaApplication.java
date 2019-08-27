package com.qf.txbb_eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class TxbbEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TxbbEurekaApplication.class, args);
    }

}
