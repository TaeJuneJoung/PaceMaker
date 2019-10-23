package com.ssafy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PacemakerApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(PacemakerApplication.class);
        app.run(args);


    }

}