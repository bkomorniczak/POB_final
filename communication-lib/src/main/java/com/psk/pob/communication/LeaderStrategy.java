package com.psk.pob.communication;

public class LeaderStrategy implements CommunicationStrategy {

  private final String leaderAddress;

  public LeaderStrategy(String leaderAddress) {
    this.leaderAddress = leaderAddress;
  }

  @Override
  public void sendMessage(String message) {
    System.out.println("Sending message to LEADER at " + leaderAddress + ": " + message);

    // Tu możesz użyć RestTemplate/WebClient itp.
    // np. restTemplate.postForObject("http://" + leaderAddress + "/api/something", message, Void.class);
  }
}
