package com.psk.pob.service7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EnableConfigurationProperties
@ComponentScan(basePackages = {
    "com.psk.pob.service7",
    "com.psk.pob.communication"
})
public class Service7Application {
  public static void main(String[] args) {
    SpringApplication.run(Service7Application.class, args);
  }
}
