package com.adobe.dc.datadogmetrics.metrics;

import com.adobe.asr.telemetry.TelemetryRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author varaj
 * Provides Datadog specific implementation for MetricsCollector.
 */
@Service
public final class DatadogMetricsCollectorImpl implements MetricsCollector {

  @Autowired
  private TelemetryRegistry telemetryRegistry;

  /**
   * Increments counter for the given key
   *
   * @param key
   */
  @Override
  public void incrementCounter(String key) {
    if(telemetryRegistry != null)
      telemetryRegistry.incrementCounter(key);
  }

  /**
   * Decrements counter for the given key
   *
   * @param key
   */
  @Override
  public void decrementCounter(String key) {
    if(telemetryRegistry != null)
      telemetryRegistry.decrementCounter(key);
  }

  /**
   * Record execution time for the given key
   *
   * @param key
   * @param executionTime
   */
  @Override
  public void recordExecutionTime(String key, long executionTime) {
    if(telemetryRegistry != null)
      telemetryRegistry.recordExecutionTime(key, executionTime);
  }
}
