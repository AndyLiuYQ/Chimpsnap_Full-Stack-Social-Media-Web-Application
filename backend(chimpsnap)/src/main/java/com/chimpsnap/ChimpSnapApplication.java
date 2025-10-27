package com.chimpsnap;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChimpSnapApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(ChimpSnapApplication.class,args);
    }
}
