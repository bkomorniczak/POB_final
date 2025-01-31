package com.psk.pob.service3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EnableConfigurationProperties
@ComponentScan(basePackages = {
    "com.psk.pob.service3",
    "com.psk.pob.communication"
})
public class Service3Application {
  public static void main(String[] args) {
    SpringApplication.run(Service3Application.class, args);
  }
}
