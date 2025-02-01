package com.psk.pob.service10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EnableConfigurationProperties
@ComponentScan(basePackages = {
    "com.psk.pob.service10",
    "com.psk.pob.communication"
})
public class Service10Application {
  public static void main(String[] args) {
    SpringApplication.run(Service10Application.class, args);
  }
}
