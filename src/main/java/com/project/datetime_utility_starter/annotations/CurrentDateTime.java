package com.project.datetime_utility_starter.annotations;

import com.project.datetime_utility_starter.utils.constants.TimezoneConstants;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CurrentDateTime {
  String timezone() default TimezoneConstants.UTC;
}
