package com.springboot.shiroo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.springboot.shiroo.dao")
public class ShirooApplication {

    public static void main(String[] args) {
        SpringApplication.run (ShirooApplication.class, args);
//          ConfigurableApplicationContext run = new SpringApplication (ShirooApplication.class).run (args);
//          SpringUtils.setApplicationContext (run);
    }
}
