package com.stock.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PreDestroy;

@SpringBootApplication(scanBasePackages ={"com.stock.*"})
@EnableJpaRepositories(basePackages = "com.stock.repository")
@EntityScan(basePackages = {"com.stock.entity"})
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class,args);
    }


}
