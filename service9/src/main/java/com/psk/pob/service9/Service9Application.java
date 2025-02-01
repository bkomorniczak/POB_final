package com.psk.pob.service9;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EnableConfigurationProperties
@ComponentScan(basePackages = {
    "com.psk.pob.service9",
    "com.psk.pob.communication"
})
public class Service9Application {
  public static void main(String[] args) {
    SpringApplication.run(Service9Application.class, args);
  }
}
