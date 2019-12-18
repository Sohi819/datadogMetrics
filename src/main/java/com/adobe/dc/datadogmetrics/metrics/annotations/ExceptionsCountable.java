package com.adobe.dc.datadogmetrics.metrics.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author varaj
 * Placing @ExceptionsCountable on a public method counts number of unhandled exceptions
 * of the target method and sends it to metrics collector.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExceptionsCountable {
}
