package org.nira.sample.sqs.hystrix.service;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.nira.sample.sqs.hystrix.exception.SampleException;
import org.nira.sample.sqs.hystrix.model.SampleResponse;
import org.springframework.stereotype.Service;

import static com.netflix.hystrix.contrib.javanica.annotation.HystrixException.RUNTIME_EXCEPTION;

@Service
@DefaultProperties(raiseHystrixExceptions = {RUNTIME_EXCEPTION})
public class SampleService {

  @HystrixCommand(commandKey = "getSampleResponse")
  public SampleResponse getSampleResponse(String value) {
    if ("123".equals(value)) {
      throw new SampleException("123");
    }
    return new SampleResponse(value);
  }
}
