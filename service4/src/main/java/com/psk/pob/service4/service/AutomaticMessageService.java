package com.psk.pob.service4.service;


import com.psk.pob.communication.CommunicationStrategy;
import com.psk.pob.service4.config.StrategyManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class AutomaticMessageService {
  private final StrategyManager strategyManager;

  private final String serviceName;

  public AutomaticMessageService(StrategyManager strategyManager,  @Value("${spring.application.name}") String serviceName) {
    this.strategyManager = strategyManager;
    this.serviceName = serviceName;
  }

  @Scheduled(fixedRate = 5000) // co 5 sek
  public void broadcastPeriodicMessage() {
    CommunicationStrategy strategy = strategyManager.getCurrentStrategy();
    strategy.sendMessage("Hello from " + serviceName);
  }
}
