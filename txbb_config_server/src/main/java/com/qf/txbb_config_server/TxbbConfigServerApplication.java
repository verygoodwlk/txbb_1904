package com.qf.txbb_config_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class TxbbConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TxbbConfigServerApplication.class, args);
    }

}
