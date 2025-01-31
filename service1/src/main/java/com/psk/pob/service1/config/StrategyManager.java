package com.psk.pob.service1.config;


import com.psk.pob.communication.CommunicationStrategy;
import com.psk.pob.communication.StrategyFactory;
import com.psk.pob.communication.StrategyType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StrategyManager {

  private final StrategyFactory strategyFactory;  // bean Springowy
  private volatile CommunicationStrategy currentStrategy;

  public StrategyManager(StrategyFactory strategyFactory) {
    this.strategyFactory = strategyFactory;
    // Na start ustawiamy np. BROADCAST:
    this.currentStrategy = strategyFactory.createStrategy(
        StrategyType.BROADCAST,
        List.of("http://service1:8081", "http://service2:8082")
    );
  }

  public void changeStrategy(StrategyType newType, List<String> nodes) {
    this.currentStrategy = strategyFactory.createStrategy(newType, nodes);
  }

  public CommunicationStrategy getCurrentStrategy() {
    return currentStrategy;
  }
}
