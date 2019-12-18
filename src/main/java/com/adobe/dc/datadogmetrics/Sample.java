package com.adobe.dc.datadogmetrics;

import com.adobe.dc.datadogmetrics.metrics.annotations.ExceptionsCountable;
import com.adobe.dc.datadogmetrics.metrics.annotations.InvocationCountable;
import com.adobe.dc.datadogmetrics.metrics.annotations.TimeTrackable;
import org.springframework.stereotype.Service;

@Service
public class Sample {
  
  @TimeTrackable
  public void sampleTimeTracking(){
    System.out.println("This is too fast!!");
  }  
  
  @InvocationCountable
  public void sampleCountingInvocations(){
    System.out.println("Once more!!");
  }  
  
  @ExceptionsCountable
  public void sampleCountingException(){
    System.out.println("I'm throwing exception!!");
    throw new RuntimeException("This is just a sample exception!!");
  }
  
}
