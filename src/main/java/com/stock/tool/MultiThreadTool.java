package com.stock.tool;

import com.stock.entity.Stock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class MultiThreadTool {

    @Bean
    public ExecutorService getThreadPool(){
        return Executors.newFixedThreadPool(10);
    }

    @Bean
    public Stock stock(){
        return new Stock();
    }

}
