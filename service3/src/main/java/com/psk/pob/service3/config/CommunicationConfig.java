package com.psk.pob.service3.config;

import com.psk.pob.communication.CommunicationStrategy;
import com.psk.pob.communication.StrategyFactory;
import com.psk.pob.communication.StrategyType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "communication")
public class CommunicationConfig {
  private String strategy;
  private List<String> nodes;
  private String leaderAddress;


  @Bean
  public CommunicationStrategy communicationStrategy() {
    return StrategyFactory.createStrategy(StrategyType.valueOf(strategy), nodes);
  }
}