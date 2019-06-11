package org.nira.sample.sqs.hystrix;

import com.netflix.hystrix.Hystrix;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

import javax.annotation.PreDestroy;

@EnableCircuitBreaker
@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @PreDestroy
  public void hystrixCleanup() {
    Hystrix.reset();
  }
}
