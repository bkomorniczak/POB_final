package com.psk.pob.service1.controllers;

import com.psk.pob.communication.CommunicationStrategy;
import com.psk.pob.service1.chaos.ChaosService;
import com.psk.pob.service1.metrics.MessageMetrics;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class CommunicationController {
  private static final Logger logger = LoggerFactory.getLogger(CommunicationController.class);

  private final CommunicationStrategy communicationStrategy;
  private final MessageMetrics messageMetrics;
  private final ChaosService chaosService;


  public CommunicationController(CommunicationStrategy communicationStrategy,
      MessageMetrics messageMetrics, ChaosService chaosService) {
    this.communicationStrategy = communicationStrategy;
    this.messageMetrics = messageMetrics;
    this.chaosService = chaosService;
  }

  @PostMapping("/send")
  public String sendMessage(@RequestBody String message) {
    communicationStrategy.sendMessage(message);
    return "Message sent using " + communicationStrategy.getClass().getSimpleName();
  }

  @PostMapping("/receive")
  public void receiveMessage(@RequestBody String message) {
    logger.info("Otrzymano wiadomosc: {}", message);
    messageMetrics.incrementReceived();  }
}
