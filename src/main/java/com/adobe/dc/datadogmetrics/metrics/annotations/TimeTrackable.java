package com.adobe.dc.datadogmetrics.metrics.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author varaj
 * Annotating a public method by @TimeTrackable captures the execution time and
 * records it into metric data against the key: classname.methodname.execution.time.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TimeTrackable {
}
