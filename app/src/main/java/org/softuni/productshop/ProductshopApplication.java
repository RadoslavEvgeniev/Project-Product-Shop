package org.softuni.productshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProductshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductshopApplication.class, args);
    }

}
