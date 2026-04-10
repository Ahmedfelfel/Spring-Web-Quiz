package com.felfel.quizservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * The type Quiz service application.
 */
@SpringBootApplication
@EnableFeignClients
public class QuizServiceApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(QuizServiceApplication.class, args);
    }

}
