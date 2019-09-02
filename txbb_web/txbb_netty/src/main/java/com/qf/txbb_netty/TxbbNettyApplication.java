package com.qf.txbb_netty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = "com.qf")
@EnableEurekaClient
public class TxbbNettyApplication {

    public static void main(String[] args) {
        SpringApplication.run(TxbbNettyApplication.class, args);
    }

}
