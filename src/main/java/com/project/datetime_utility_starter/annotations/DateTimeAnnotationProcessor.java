package com.project.datetime_utility_starter.annotations;

import com.project.datetime_utility_starter.utils.enums.ErrorsEnum;
import com.project.datetime_utility_starter.utils.exceptions.DateTimeUtilException;
import com.project.datetime_utility_starter.utils.formats.DateFormats;
import com.project.datetime_utility_starter.utils.response.ErrorResponse;
import java.lang.reflect.Field;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class DateTimeAnnotationProcessor implements BeanPostProcessor {

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) {
    try {
      for (Field field : bean.getClass().getDeclaredFields()) {
        field.setAccessible(true);
        processCurrentDateAnnotation(field, bean);
        processCurrentDateTimeAnnotation(field, bean);
      }
    } catch (DateTimeUtilException exception) {
      throw new BeanCreationException(exception.getErrorResponse().getMessage());
    }
    return bean;
  }

  private void processCurrentDateAnnotation(Field field, Object bean) throws DateTimeUtilException {
    if (field.isAnnotationPresent(CurrentDate.class)) {
      CurrentDate currentDateAnnotation = field.getAnnotation(CurrentDate.class);
      final String timezone = currentDateAnnotation.timezone();
      final String format = currentDateAnnotation.format();

      if (!isValidTimezone(timezone)) {
        throw new DateTimeUtilException(
            new ErrorResponse(ErrorsEnum.INVALID_TIMEZONE.getMessage()));
      }

      if (!isValidDateFormat(format)) {
        throw new DateTimeUtilException(new ErrorResponse(ErrorsEnum.INVALID_FORMAT.getMessage()));
      }
      setFieldValue(field, bean, timezone, format);
    }
  }

  private void processCurrentDateTimeAnnotation(Field field, Object bean)
      throws DateTimeUtilException {
    if (field.isAnnotationPresent(CurrentDateTime.class)) {
      CurrentDateTime currentDateAnnotation = field.getAnnotation(CurrentDateTime.class);
      final String timezone = currentDateAnnotation.timezone();

      if (!isValidTimezone(timezone)) {
        throw new DateTimeUtilException(ErrorsEnum.INVALID_TIMEZONE.getMessage());
      }
      setFieldValue(field, bean, timezone, DateFormats.FORMAT_ISO_DATETIME);
    }
  }

  private void setFieldValue(Field field, Object bean, String timezone, String format)
      throws DateTimeUtilException {
    try {
      if (!field.getType().equals(String.class)) {
        throw new DateTimeUtilException(
            new ErrorResponse(ErrorsEnum.UNSUPPORTED_FIELD_TYPE.getMessage()));
      }

      ZonedDateTime now = ZonedDateTime.now(ZoneId.of(timezone));
      final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
      field.set(bean, now.format(formatter));

    } catch (IllegalAccessException e) {
      throw new DateTimeUtilException(
          new ErrorResponse(ErrorsEnum.FIELD_VALUE_SET_FAILED.getMessage()));
    }
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) {
    return bean;
  }

  private boolean isValidTimezone(final String timezone) {
    try {
      ZoneId.of(timezone);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  private boolean isValidDateFormat(final String format) {
    try {
      DateTimeFormatter.ofPattern(format);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
