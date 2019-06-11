package org.nira.sample.sqs.hystrix.handler;

import com.netflix.hystrix.exception.HystrixRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.nira.sample.sqs.hystrix.exception.SampleException;
import org.nira.sample.sqs.hystrix.model.SampleResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

import static com.netflix.hystrix.exception.HystrixRuntimeException.FailureType.TIMEOUT;

@Slf4j
@ControllerAdvice
public class SampleExceptionHandler {

  @ExceptionHandler({ HystrixRuntimeException.class })
//  public ResponseEntity<SampleResponse> handleHystrixRuntimeException(HystrixRuntimeException exception) {
  public void handleHystrixRuntimeException(HystrixRuntimeException exception) {
    String newMsg;
    if (exception.getFailureType() == TIMEOUT) {
      newMsg = "Timeout Error: ".concat(exception.getMessage());
    } else {
      if (exception.getCause() instanceof SampleException) {
        newMsg = "Sample Exception: ".concat(exception.getCause().getMessage());
      } else {
        newMsg = "Server Error: ".concat(exception.getMessage());
      }
    }
    log.error("Hystrix Exception: {}", newMsg);
//    return new ResponseEntity<>(new SampleResponse(newMsg), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler({ SampleException.class })
  public void handleSampleException(SampleException exception) {
    log.error("Sample Exception: {}", exception.getMessage());
  }
}
