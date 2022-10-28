package org.huhu.spring.security.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

import java.util.TimeZone;

import static java.time.ZoneOffset.UTC;

@SpringBootApplication(exclude = UserDetailsServiceAutoConfiguration.class)
public class App {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone(UTC));
        SpringApplication.run(App.class, args);
    }

}
