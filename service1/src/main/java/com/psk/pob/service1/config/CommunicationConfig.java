package com.psk.pob.service1.config;

import com.psk.pob.communication.BroadcastStrategy;
import com.psk.pob.communication.CommunicationStrategy;
import com.psk.pob.communication.LeaderStrategy;
import com.psk.pob.communication.RoundRobinStrategy;
import com.psk.pob.communication.StrategyType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CommunicationConfig {

  @Value("${communication.strategy}")
  private StrategyType strategyType;

  @Value("${communication.leader-address:}")
  private String leaderAddress;

  // Wczytujemy listę adresów z configu (np. service2:8082, service3:8083)
  @Value("${communication.addresses:}")
  private List<String> addresses;

  @Bean
  public CommunicationStrategy communicationStrategy() {
    switch (strategyType) {
      case LEADER:
        return new LeaderStrategy(leaderAddress);
      case BROADCAST:
        return new BroadcastStrategy(addresses);
      case ROUND_ROBIN:
        return new RoundRobinStrategy(addresses);
      default:
        return new BroadcastStrategy(addresses);
    }
  }
}
