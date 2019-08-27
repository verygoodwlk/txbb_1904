package com.qf.txbb_user;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages = "com.qf")
@EnableEurekaClient
@MapperScan("com.qf.dao")
@Import(FdfsClientConfig.class)
public class TxbbUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(TxbbUserApplication.class, args);
    }

}
