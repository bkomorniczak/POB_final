package com.psk.pob.service1.controllers;

import com.psk.pob.communication.CommunicationStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  private final CommunicationStrategy communicationStrategy;

  public HelloController(CommunicationStrategy communicationStrategy) {
    this.communicationStrategy = communicationStrategy;
  }

  @GetMapping("/trigger")
  public String triggerCommunication() {
    communicationStrategy.sendMessage("Hello from Service1!");
    return "Message triggered from Service1!";
  }
}
