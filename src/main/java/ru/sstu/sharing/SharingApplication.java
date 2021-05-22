package ru.sstu.sharing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SharingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SharingApplication.class, args);
    }

}
