package com.psk.pob.service1.service;

import com.psk.pob.communication.CommunicationStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class AutomaticMessageService {

  private final CommunicationStrategy communicationStrategy;
  private final String serviceName;


  public AutomaticMessageService(CommunicationStrategy communicationStrategy, @Value("${spring.application.name}") String serviceName) {
    this.communicationStrategy = communicationStrategy;
    this.serviceName = serviceName;
  }

  @Scheduled(fixedRate = 5000) // co 5 sek
  public void broadcastPeriodicMessage() {
    String message = "Cześć, tu " + serviceName + "! Godzina = " + System.currentTimeMillis();
    communicationStrategy.sendMessage(message);
  }
}
