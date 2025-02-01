package com.psk.pob.service7.controllers;

import com.psk.pob.communication.StrategyType;
import com.psk.pob.service7.config.StrategyManager;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/strategy")
public class StrategyController {

  private final StrategyManager strategyManager;

  public StrategyController(StrategyManager strategyManager) {
    this.strategyManager = strategyManager;
  }

  @PostMapping("/change")
  public String changeStrategy(@RequestParam String newStrategy,
      @RequestParam(required = false) List<String> nodes)
 {
    StrategyType type = StrategyType.valueOf(newStrategy.toUpperCase());

    if (nodes == null || nodes.isEmpty()) {
      nodes = List.of("http://service1:8081", "http://service2:8082");
    }

    strategyManager.changeStrategy(type, nodes);
    return "Strategy changed to " + newStrategy;
  }

  @GetMapping("/current")
  public String getCurrentStrategy() {
    return "Current strategy: " + strategyManager.getCurrentStrategy().getClass().getSimpleName();
  }
}
