package com.psk.pob.service1.controllers;


import com.psk.pob.service1.chaos.ChaosService;
import com.psk.pob.service1.config.ChaosConfig;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chaos")
public class ChaosController {

  private final ChaosConfig chaosConfig;

  public ChaosController(ChaosConfig chaosConfig) {
    this.chaosConfig = chaosConfig;
  }

  @PostMapping("/delay")
  public String setDelay(@RequestParam long delayMillis) {
    chaosConfig.setDelayMillis(delayMillis);
    return "Ustawiono delayMillis na " + delayMillis;
  }

  @PostMapping("/overload")
  public String setOverload(@RequestParam boolean overloadActive) {
    chaosConfig.setOverloadActive(overloadActive);
    return "Ustawiono overloadActive na " + overloadActive;
  }
}
