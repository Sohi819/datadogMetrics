package com.adobe.dc.datadogmetrics.metrics;

/**
 * @author varaj
 * MetricsCollector provides methods for collecting various metrics.
 */
public interface MetricsCollector {
  /**
   * Increaments counter for the given key
   * @param key
   */
  void incrementCounter(String key);

  /**
   * Decreament counter for the given key
   * @param key
   */
  void decrementCounter(String key);

  /**
   * Record execution time for the given key
   * @param key
   * @param executionTime
   */
  void recordExecutionTime(String key, long executionTime);
}
