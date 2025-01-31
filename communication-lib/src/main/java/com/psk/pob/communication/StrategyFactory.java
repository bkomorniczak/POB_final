package com.psk.pob.communication;

import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class StrategyFactory {


  public static CommunicationStrategy createStrategy(StrategyType strategyType,
      List<String> addresses
  ) {
    switch (strategyType) {
      case LEADER:
        if (addresses.isEmpty()) {
          throw new IllegalArgumentException(
              "Leader strategy requires at least one address (leader).");
        }
        return new LeaderStrategy(addresses.get(0));
      case BROADCAST:
        return new BroadcastStrategy(addresses);
      case ROUND_ROBIN:
        return new RoundRobinStrategy(addresses);
      default:
        throw new UnsupportedOperationException("Unknown strategy type: " + strategyType);
    }
  }
}
