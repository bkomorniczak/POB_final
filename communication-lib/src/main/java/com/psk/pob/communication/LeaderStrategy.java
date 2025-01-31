package com.psk.pob.communication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class LeaderStrategy implements CommunicationStrategy {
  private static final Logger logger = LoggerFactory.getLogger(LeaderStrategy.class);

  private final String leaderAddress;
  private final RestTemplate restTemplate;

  public LeaderStrategy(String leaderAddress) {
    this.leaderAddress = leaderAddress;
    this.restTemplate = new RestTemplate();
  }

  @Override
  public void sendMessage(String message) {

    logger.info("LeaderStrategy: wysyłam wiadomość do leadera: {}", leaderAddress);
    try {
      restTemplate.postForObject(leaderAddress + "/receive", message, String.class);
    } catch (Exception e) {
      logger.error("Błąd podczas wysyłania do leadera: {}", leaderAddress, e);
    }
  }
}
