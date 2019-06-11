package org.nira.sample.sqs.hystrix.listeners;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nira.sample.sqs.hystrix.model.SampleResponse;
import org.nira.sample.sqs.hystrix.service.SampleService;
import org.springframework.cloud.aws.messaging.listener.Acknowledgment;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class QueueListener {

  private final SampleService sampleService;

  @SqsListener(value = "sample-queue", deletionPolicy = SqsMessageDeletionPolicy.NEVER)
  public void receiveMsg(String payload, Acknowledgment acknowledgment) {
    SampleResponse sampleResponse = sampleService.getSampleResponse(payload);
    log.info(sampleResponse.getMessage());
    acknowledgment.acknowledge();
  }
}
