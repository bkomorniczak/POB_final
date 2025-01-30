package com.psk.pob.communication;

import java.util.List;

public class BroadcastStrategy implements CommunicationStrategy {

  private final List<String> addresses;

  public BroadcastStrategy(List<String> addresses) {
    this.addresses = addresses;
  }

  @Override
  public void sendMessage(String message) {
    for (String addr : addresses) {
      System.out.println("Broadcast to " + addr + ": " + message);

      // restTemplate.postForObject("http://" + addr + "/api/something", message, Void.class);
    }
  }
}
