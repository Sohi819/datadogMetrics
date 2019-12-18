package com.adobe.dc.datadogmetrics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DatadogmetricsApplication {
  public static void main(String[] args) {
    ApplicationContext applicationContext = SpringApplication.run(DatadogmetricsApplication.class, args);
    Sample sample = applicationContext.getBean(Sample.class);
    sample.sampleTimeTracking();
    sample.sampleCountingInvocations();
    sample.sampleCountingException();
  }

}
