package com.psk.pob.service1.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "chaos")
public class ChaosConfig {
  private long delayMillis;
  private boolean overloadActive;

  public long getDelayMillis() { return delayMillis; }
  public void setDelayMillis(long delayMillis) { this.delayMillis = delayMillis; }

  public boolean isOverloadActive() { return overloadActive; }
  public void setOverloadActive(boolean overloadActive) { this.overloadActive = overloadActive; }
}
