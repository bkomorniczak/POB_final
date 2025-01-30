package com.psk.pob.communication;

import com.psk.pob.communication.CommunicationStrategy;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RoundRobinStrategy implements CommunicationStrategy {

  private final List<String> addresses;
  private final AtomicInteger index = new AtomicInteger(0);

  public RoundRobinStrategy(List<String> addresses) {
    this.addresses = addresses;
  }

  @Override
  public void sendMessage(String message) {
    if (addresses.isEmpty()) {
      System.out.println("No addresses to send via RoundRobin");
      return;
    }
    int currentIndex = index.getAndUpdate(i -> (i + 1) % addresses.size());
    String target = addresses.get(currentIndex);
    System.out.println("RoundRobin to " + target + ": " + message);

    // restTemplate.postForObject("http://" + target + "/api/something", message, Void.class);
  }
}
