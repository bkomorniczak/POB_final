package com.psk.pob.service2.config;


import com.psk.pob.communication.CommunicationStrategy;
import com.psk.pob.communication.StrategyFactory;
import com.psk.pob.communication.StrategyType;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class StrategyManager {

  private final StrategyFactory strategyFactory;  // bean Springowy
  private volatile CommunicationStrategy currentStrategy;

  public StrategyManager(StrategyFactory strategyFactory) {
    this.strategyFactory = strategyFactory;
    // Na start ustawiamy np. BROADCAST:
    this.currentStrategy = strategyFactory.createStrategy(
        StrategyType.BROADCAST,
        List.of("http://service1:8081",
            "http://service2:8082",
            "http://service3:8083",
            "http://service4:8084",
            "http://service5:8085",
            "http://service6:8086",
            "http://service7:8087",
            "http://service8:8088",
            "http://service9:8089",
            "http://service10:8099"
        )
    );
  }

  public void changeStrategy(StrategyType newType, List<String> nodes) {
    this.currentStrategy = strategyFactory.createStrategy(newType, nodes);
  }

  public CommunicationStrategy getCurrentStrategy() {
    return currentStrategy;
  }
}
