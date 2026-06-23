package com.tuan.lla;

import com.tuan.lla.model.User;
import com.tuan.lla.service.impl.UserServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class LanguageLearningAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(LanguageLearningAppApplication.class, args);
    }
}
