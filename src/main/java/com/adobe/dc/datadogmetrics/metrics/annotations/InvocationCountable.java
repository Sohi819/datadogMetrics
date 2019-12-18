package com.adobe.dc.datadogmetrics.metrics.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author varaj
 * Placing @InvocationCountable on a public method counts number of invocations
 * of the target method and sends it to metrics collector against the key: classname.methodname.execution.counter
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InvocationCountable {
}
