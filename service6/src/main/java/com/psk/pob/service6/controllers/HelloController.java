package com.psk.pob.service6.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @GetMapping("/info")
  public String info() {
    return "Hello from Service6! I'm possibly the leader if so configured.";
  }
}
