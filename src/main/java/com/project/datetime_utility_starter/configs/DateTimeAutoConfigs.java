package com.project.datetime_utility_starter.configs;

import com.project.datetime_utility_starter.annotations.DateTimeAnnotationProcessor;
import com.project.datetime_utility_starter.utils.impl.DateTimeUtilsImpl;
import com.project.datetime_utility_starter.utils.service.DateTimeUtils;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@ConditionalOnMissingBean(DateTimeUtils.class)
public class DateTimeAutoConfigs {

  @Bean
  public DateTimeUtils dateTimeUtils() {
    return new DateTimeUtilsImpl();
  }

  @Bean
  public DateTimeAnnotationProcessor dateTimeAnnotationProcessor() {
    return new DateTimeAnnotationProcessor();
  }
}
