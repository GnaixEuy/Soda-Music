package cn.gnaixeuy.sodamusic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SodaMusicApplication {

    public static void main(String[] args) {
        SpringApplication.run(SodaMusicApplication.class, args);
    }

}