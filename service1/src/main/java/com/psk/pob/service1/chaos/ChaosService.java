package com.psk.pob.service1.chaos;

import com.psk.pob.service1.config.ChaosConfig;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

@Service
public class ChaosService {
  private final ChaosConfig chaosConfig;
  private final Counter chaosInvocationsCounter;


  public ChaosService(ChaosConfig chaosConfig, MeterRegistry meterRegistry) {
    this.chaosConfig = chaosConfig;
    this.chaosInvocationsCounter = Counter.builder("chaos_invocations_total")
        .description("Ile razy wywołano chaos (delay/overload)")
        .register(meterRegistry);  }

  public void simulateChaos() {
    chaosInvocationsCounter.increment();

    simulateDelay();
    simulateOverload();
  }

  private void simulateDelay() {
    long delay = chaosConfig.getDelayMillis();
    if (delay > 0) {
      try {
        Thread.sleep(delay);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
  }

  private void simulateOverload() {
    if (chaosConfig.isOverloadActive()) {
      // sztuczne obciążenie CPU
      double sum = 0;
      for (int i = 0; i < 2_000_000; i++) {
        sum += Math.sqrt(i);
      }
    }
  }
}
