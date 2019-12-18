package com.adobe.dc.datadogmetrics.metrics.aspects;

import com.adobe.dc.datadogmetrics.metrics.MetricsCollector;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

/**
 * @author varaj
 *         Provides implementaions for the following metrics annotations:
 * @TimeTrackable
 * @InvocationCountable
 * @ExceptionsCountable
 */
@Aspect
@Configuration
public class DatadogMetricsResourceAspects {


  private static final org.apache.log4j.Logger logger = Logger.getLogger(DatadogMetricsResourceAspects.class.getName());
  private static final String EXECUTION_COUNTER = "execution.counter";
  private static final String EXCEPTION_COUNTER = "exception.counter";
  private static final String EXECUTION_TIME = "execution.time";
  private static final String DOT = ".";

  @Autowired
  private MetricsCollector metricsCollector;

  /**
   * Provides implementation for @TimeTrackable
   * @param joinPoint
   * @return Object
   * @throws Throwable
   */
  @Around(value = "@annotation(com.adobe.dc.datadogmetrics.metrics.annotations.TimeTrackable)")
  public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
    String className = getClassName(joinPoint);
    String methodName = getMethodName(joinPoint);
    String key = null;
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    Object response = joinPoint.proceed();


    stopWatch.stop();
    if (className != null && methodName != null) {
      key = new StringBuilder(className).append(DOT).append(methodName).append(EXECUTION_TIME).toString();
      metricsCollector.recordExecutionTime(key, stopWatch.getTotalTimeMillis());
      logger.debug("Pushing data to datadog for key: " + key);
    }
    return response;
  }

  /**
   * Provides implementation for @InvocationCountable
   * @param joinPoint
   * @throws Throwable
   */
  @Before(value = "@annotation(com.adobe.dc.datadogmetrics.metrics.annotations.InvocationCountable)")
  public void countInvocations(JoinPoint joinPoint) throws Throwable {
    String className = getClassName(joinPoint);
    String methodName = getMethodName(joinPoint);
    String key = null;

    if (className != null && methodName != null) {
      key = new StringBuilder(className).append(DOT).append(methodName).append(EXECUTION_COUNTER).toString();
      metricsCollector.incrementCounter(key);
      logger.debug("Pushing data to datadog for key: " + key);
    }
  }

  /**
   * Provides implementation for @ExceptionsCountable
   * @param joinPoint
   * @throws Throwable
   */
  @AfterThrowing(value = "@annotation(com.adobe.dc.datadogmetrics.metrics.annotations.ExceptionsCountable)")
  public void countUnhandledExceptions(JoinPoint joinPoint) throws Throwable {
    String className = getClassName(joinPoint);
    String methodName = getMethodName(joinPoint);
    String key = null;

    if (className != null && methodName != null) {
      key = new StringBuilder(className).append(DOT).append(methodName).append(EXCEPTION_COUNTER).toString();
      metricsCollector.incrementCounter(key);
      logger.debug("Pushing data to datadog for key: " + key);
    }
  }

  private String getClassName(JoinPoint joinPoint) {
    String className = null;
    Object target = joinPoint.getTarget();

    if (target != null) {
      className = target.getClass().getName().toLowerCase();
    }

    return className;
  }

  private String getMethodName(JoinPoint joinPoint) {
    String methodName = null;

    Signature signature = joinPoint.getSignature();
    if (signature != null) {
      methodName = signature.getName().toLowerCase();
    }

    return methodName;
  }

}
