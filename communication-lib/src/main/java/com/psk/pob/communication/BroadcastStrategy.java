package com.psk.pob.communication;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class BroadcastStrategy implements CommunicationStrategy {
  private static final Logger logger = LoggerFactory.getLogger(BroadcastStrategy.class);
  private final List<String> nodes;
  private final RestTemplate restTemplate;


  public BroadcastStrategy(List<String> nodes) {
    this.nodes = nodes;
    this.restTemplate = new RestTemplate();
  }

  @Override
  public void sendMessage(String message) {

    logger.info("BroadcastStrategy: wysyłam wiadomość do wszystkich węzłów.");
    for (String node : nodes) {
      try {
        restTemplate.postForObject(node + "/receive", message, String.class);
      } catch (Exception e) {
        logger.error("Błąd podczas wysyłania do węzła: {}", node, e);
      }
    }
  }
}