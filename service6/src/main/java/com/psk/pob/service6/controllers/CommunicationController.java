package com.psk.pob.service6.controllers;

import com.psk.pob.communication.CommunicationStrategy;
import com.psk.pob.service6.metrics.MessageMetrics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommunicationController {
  private static final Logger logger = LoggerFactory.getLogger(
      com.psk.pob.service6.controllers.CommunicationController.class);

  private final CommunicationStrategy communicationStrategy;
  private final MessageMetrics messageMetrics;

  public CommunicationController(CommunicationStrategy communicationStrategy,
      MessageMetrics messageMetrics) {
    this.communicationStrategy = communicationStrategy;
    this.messageMetrics = messageMetrics;
  }

  @PostMapping("/send")
  public String sendMessage(@RequestBody String message) {
    communicationStrategy.sendMessage(message);
    return "Message sent using " + communicationStrategy.getClass().getSimpleName();
  }

  @PostMapping("/receive")
  public void receiveMessage(@RequestBody String message) {
    logger.info("Otrzymano wiadomość: {}", message);
    messageMetrics.incrementReceived();  }
}
