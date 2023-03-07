package com.example.sz;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SzApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SzApplication.class)
                .headless(false)
                .run(args);
    }


}
