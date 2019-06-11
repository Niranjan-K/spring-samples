package org.nira.sample.sqs.hystrix.exception;

public class SampleException extends RuntimeException {
  public SampleException(String msg) {
    super(msg);
  }

  public SampleException(String msg, Throwable cause) {
    super(msg, cause);
  }
}
