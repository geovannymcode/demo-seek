package com.geovannycode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class DemoSeekApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoSeekApplication.class, args);
    }
}
