package com.psk.pob.service1.chaos;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import java.io.IOException;
import org.springframework.stereotype.Component;

@Component
public class ChaosFilter implements Filter {

  private final ChaosService chaosService;

  public ChaosFilter(ChaosService chaosService) {
    this.chaosService = chaosService;
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response,
      FilterChain chain) throws IOException, ServletException {
    chaosService.simulateChaos();

    chain.doFilter(request, response);
  }
}
