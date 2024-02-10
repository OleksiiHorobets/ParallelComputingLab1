package org.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@Slf4j
@SpringBootApplication
public class Main {

  public static void main(String[] args) {
    new SpringApplicationBuilder(Main.class)
        .web(WebApplicationType.NONE)
        .headless(false)
        .run(args);
  }

}