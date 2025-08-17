package com.elsasen.youtubetemachecker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication(scanBasePackages = "com.elsasen.youtubetemachecker")
public class YoutubeTemaCheckerApplication {

    public static void main(String[] args) {
        SpringApplication.run(YoutubeTemaCheckerApplication.class, args);
    }

}
