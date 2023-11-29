package com.news;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class NewsApplication {
    public static void main(String[] args) {
        System.out.println("http://localhost:8089/swagger-ui.html");
        SpringApplication.run(NewsApplication.class, args);
    }
}
