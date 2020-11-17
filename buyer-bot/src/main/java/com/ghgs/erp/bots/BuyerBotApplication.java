package com.ghgs.erp.bots;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BuyerBotApplication {

  public static void main(String[] args) {
    SpringApplication.run(BuyerBotApplication.class, args);
  }

}
