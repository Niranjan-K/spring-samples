package org.nira.sample.sqs.hystrix.controller;

import lombok.RequiredArgsConstructor;
import org.nira.sample.sqs.hystrix.model.SampleResponse;
import org.nira.sample.sqs.hystrix.service.SampleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(produces = APPLICATION_JSON_UTF8_VALUE)
public class SampleController {

  private final SampleService sampleService;

  @GetMapping("/hello/{value}")
  public SampleResponse getSampleResponse(
      @PathVariable String value) {
    return sampleService.getSampleResponse(value);
  }
}
