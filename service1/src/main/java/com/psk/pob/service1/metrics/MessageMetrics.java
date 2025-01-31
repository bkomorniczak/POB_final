package com.psk.pob.service1.metrics;


import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class MessageMetrics {
  private final Counter messagesReceived;

  public MessageMetrics(MeterRegistry registry) {
    this.messagesReceived = Counter.builder("app_messages_received_total")
        .description("Liczba odebranych wiadomości")
        .register(registry);
  }

  public void incrementReceived() {
    messagesReceived.increment();
  }
}
