package com.project.datetime_utility_starter.annotations;

import com.project.datetime_utility_starter.utils.constants.TimezoneConstants;
import com.project.datetime_utility_starter.utils.formats.DateFormats;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CurrentDate {

  String timezone() default TimezoneConstants.UTC;

  String format() default DateFormats.FORMAT_ISO_DATE;
}
