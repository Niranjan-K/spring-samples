package org.nira.sample.sqs.hystrix.config;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.config.SimpleMessageListenerContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
@Slf4j
public class AwsConfig {

  private String awsSqsEndpoint = "http://localhost:4576";
  private int queueTimeout = 30;
  String awsAccessKey = "foo";
  String awsSecretKey = "bar";
  private static final Regions REGIONS = Regions.AP_SOUTHEAST_2;

  @Bean
  @Primary
  public AmazonSQSAsync amazonSqs() {
    log.debug("Initializing SQS mock client");

    //Work around as this value is not being set in spring cloud
    System.setProperty("aws.accessKeyId", awsAccessKey);
    System.setProperty("aws.secretKey", awsSecretKey);

    return AmazonSQSAsyncClientBuilder
        .standard()
        .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(awsSqsEndpoint, REGIONS.getName()))
        .build();
  }

  @Bean
  public SimpleMessageListenerContainerFactory simpleMessageListenerContainerFactory() {
    SimpleMessageListenerContainerFactory factory = new SimpleMessageListenerContainerFactory();
    factory.setVisibilityTimeout(queueTimeout);
    return factory;
  }
}
