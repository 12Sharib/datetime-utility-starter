package com.project.datetime_utility_starter.utils.impl;

import com.project.datetime_utility_starter.utils.enums.ErrorsEnum;
import com.project.datetime_utility_starter.utils.exceptions.DateTimeUtilException;
import com.project.datetime_utility_starter.utils.formats.DateFormats;
import com.project.datetime_utility_starter.utils.service.DateTimeUtils;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.util.Strings;

/**
 * @author Mohd Sharib Saifi
 * @version 1.0.0
 */
@Log4j2
public class DateTimeUtilsImpl implements DateTimeUtils {

  private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern(
      DateFormats.FORMAT_ISO_DATE);

  @Override
  public List<String> getDatesInRangeByUnit(
      @NonNull String startDate,
      @NonNull String endDate,
      @NonNull String unit,
      @NonNull String desiredFormat)
      throws DateTimeUtilException {
    // Validate dates is not empty
    validateBothDatesNotEmpty(startDate, endDate);

    // Validate desired Format
    if (!isValidFormats(desiredFormat)) {
      throw new DateTimeUtilException(
          ErrorsEnum.INVALID_DESIRED_FORMAT.getMessage());
    }

    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(desiredFormat);

    LocalDate start;
    LocalDate end;

    try {
      start = LocalDate.parse(startDate, formatter);
      end = LocalDate.parse(endDate, formatter);
    } catch (DateTimeParseException exception) {
      throw new DateTimeUtilException(
          ErrorsEnum.INVALID_DATE_FORMAT.getMessage());
    }

    // Validate end date before current date
    validateEndDateBeforeStartDate(start, end);

    return switch (unit.toLowerCase()) {
      case "days" -> start.datesUntil(end.plusDays(1)).map(date -> date.format(formatter)).toList();
      case "weeks" -> start
          .datesUntil(end.plusDays(1))
          .filter(date -> date.getDayOfWeek() == DayOfWeek.MONDAY)
          .map(date -> date.format(formatter))
          .toList();
      case "months" -> Stream.iterate(start.withDayOfMonth(1), date -> date.plusMonths(1))
          .takeWhile(date -> !date.isAfter(end))
          .map(date -> date.format(formatter))
          .toList();
      default -> throw new DateTimeUtilException(ErrorsEnum.INVALID_UNIT.getMessage());
    };
  }

  @Override
  public Long calculateBusinessDays(@NonNull final String startDate, @NonNull final String endDate)
      throws DateTimeUtilException {
    log.debug("Entry inside @class DateTimeUtilsImpl @method calculateBusinessDays");
    // Validate dates is not empty
    validateBothDatesNotEmpty(startDate, endDate);

    LocalDate start;
    LocalDate end;

    try {
      start = parseDateInDefaultFormat(startDate);
      end = parseDateInDefaultFormat(endDate);
    } catch (DateTimeParseException exception) {
      throw new DateTimeUtilException(
          ErrorsEnum.INVALID_DATE_FORMAT.getMessage());
    }

    // Validate end date is not before start date
    validateEndDateBeforeStartDate(start, end);

    // Filter only weekdays (Monday to Friday)
    return start.datesUntil(end.plusDays(1))
        .filter(date -> {
          DayOfWeek day = date.getDayOfWeek();
          return day != DayOfWeek.SATURDAY && day != DayOfWeek.SUNDAY;
        })
        .count();
  }

  @Override
  public Long calculateWeekends(@NonNull final String startDate, @NonNull final String endDate)
      throws DateTimeUtilException {
    log.debug("Entry inside @class DateTimeUtilsImpl @method calculateWeekends");

    // Validate dates is not empty
    validateBothDatesNotEmpty(startDate, endDate);

    LocalDate start;
    LocalDate end;
    try {
      start = parseDateInDefaultFormat(startDate);
      end = parseDateInDefaultFormat(endDate);
    } catch (DateTimeParseException e) {
      throw new DateTimeUtilException(ErrorsEnum.INVALID_DATE_FORMAT.getMessage());
    }

    // Validate end date before start date
    validateEndDateBeforeStartDate(start, end);

    try {
      long weekendsCount = 0;

      LocalDate current = start;
      while (!current.isAfter(end)) {
        DayOfWeek day = current.getDayOfWeek();
        if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
          weekendsCount++;
        }
        current = current.plusDays(1);
      }

      log.debug("Number of weekends between {} and {} is {}", startDate, endDate, weekendsCount);
      return weekendsCount;

    } catch (Exception e) {
      log.error("An error occurred while calculating weekends");
      throw new DateTimeUtilException("Error calculating weekends.");
    }
  }

  private void validateBothDatesNotEmpty(final String startDate, final String endDate)
      throws DateTimeUtilException {
    if (Strings.isEmpty(startDate) || Strings.isEmpty(endDate)) {
      throw new DateTimeUtilException(ErrorsEnum.INVALID_DATE_FORMAT.getMessage());
    }
  }

  @Override
  public Map<String, Long> calculateDaysMonthsYears(@NonNull String startDate,
      @NonNull String endDate)
      throws DateTimeUtilException {
    log.debug("Entry inside @class DateTimeUtilsImpl @method calculateDaysMonthsYears");

    // Validate dates is not empty
    validateBothDatesNotEmpty(startDate, endDate);

    LocalDate start;
    LocalDate end;
    try {
      start = parseDateInDefaultFormat(startDate);
      end = parseDateInDefaultFormat(endDate);
    } catch (DateTimeParseException e) {
      throw new DateTimeUtilException(ErrorsEnum.INVALID_DATE_FORMAT.getMessage());
    }

    // Validate end date before start date
    validateEndDateBeforeStartDate(start, end);

    final Period period = Period.between(start, end);
    long years = period.getYears();
    long months = period.getMonths();
    long days = period.getDays();

    final Map<String, Long> result = new HashMap<>();
    result.put("years", years);
    result.put("months", months);
    result.put("days", days);
    return result;
  }

  @Override
  public Boolean isExistsInLeapYear(@NonNull String date) throws DateTimeUtilException {
    log.debug("Entry inside @class DateTimeUtilsImpl @method isExistsInLeapYear");

    // Validate date is not empty
    validateDateNotEmpty(date);

    LocalDate localDate;
    try {
      localDate = parseDateInDefaultFormat(date);
      if (!localDate.format(DEFAULT_FORMATTER).equals(date)) {
        throw new DateTimeUtilException(ErrorsEnum.INVALID_DATE.getMessage());
      }
      return localDate.isLeapYear();
    } catch (DateTimeParseException e) {
      throw new DateTimeUtilException(ErrorsEnum.INVALID_DATE_FORMAT.getMessage());
    }

  }

  @Override
  public String formatCurrentDate(@NonNull String desiredFormat) throws DateTimeUtilException {
    log.debug("Entry inside @class DateTimeUtilsImpl @method formatCurrentDate");

    if (!isValidFormats(desiredFormat)) {
      throw new DateTimeUtilException(
          ErrorsEnum.INVALID_DATE_FORMAT.getMessage());
    }

    if (desiredFormat.contains("Z") || desiredFormat.contains("XXX")) {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern(desiredFormat);
      ZonedDateTime now = ZonedDateTime.now();
      return now.format(formatter);
    } else {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern(desiredFormat);
      LocalDateTime now = LocalDateTime.now();
      return now.format(formatter);
    }
  }

  @Override
  public String addDaysToDate(@NonNull String dateStr, int days) throws DateTimeUtilException {
    // Validate date is not empty
    validateDateNotEmpty(dateStr);

    return parseDateInDefaultFormat(dateStr).plusDays(days).format(DEFAULT_FORMATTER);
  }

  @Override
  public String subtractDaysFromDate(@NonNull String dateStr, int days)
      throws DateTimeUtilException {
    // Validate date is not empty
    validateDateNotEmpty(dateStr);

    return parseDateInDefaultFormat(dateStr).minusDays(days).format(DEFAULT_FORMATTER);
  }

  @Override
  public String addMonthsToDate(@NonNull String dateStr, int months) throws DateTimeUtilException {
    // Validate date is not empty
    validateDateNotEmpty(dateStr);

    return parseDateInDefaultFormat(dateStr).plusMonths(months).format(DEFAULT_FORMATTER);
  }

  @Override
  public String addYearsToDate(@NonNull String dateStr, int years) throws DateTimeUtilException {
    // Validate date is not empty
    validateDateNotEmpty(dateStr);

    return parseDateInDefaultFormat(dateStr).plusYears(years).format(DEFAULT_FORMATTER);
  }

  @Override
  public String getFirstDayOfMonth(@NonNull String dateStr) throws DateTimeUtilException {
    // Validate date is not empty
    validateDateNotEmpty(dateStr);

    return parseDateInDefaultFormat(dateStr).withDayOfMonth(1).format(DEFAULT_FORMATTER);
  }

  @Override
  public String getLastDayOfMonth(@NonNull String dateStr) throws DateTimeUtilException {
    // Validate date is not empty
    validateDateNotEmpty(dateStr);

    return parseDateInDefaultFormat(dateStr).with(TemporalAdjusters.lastDayOfMonth())
        .format(DEFAULT_FORMATTER);
  }

  @Override
  public String getFirstDayOfYear(@NonNull String dateStr) throws DateTimeUtilException {
    // Validate date is not empty
    validateDateNotEmpty(dateStr);

    return parseDateInDefaultFormat(dateStr).withDayOfYear(1).format(DEFAULT_FORMATTER);
  }

  @Override
  public String getLastDayOfYear(@NonNull String dateStr) throws DateTimeUtilException {
    // Validate date is not empty
    validateDateNotEmpty(dateStr);

    return parseDateInDefaultFormat(dateStr).with(TemporalAdjusters.lastDayOfYear())
        .format(DEFAULT_FORMATTER);
  }

  @Override
  public String getNextWeekday(@NonNull String dateStr, @NonNull DayOfWeek dayOfWeek)
      throws DateTimeUtilException {
    // Validate date is not empty
    validateDateNotEmpty(dateStr);

    return parseDateInDefaultFormat(dateStr).with(TemporalAdjusters.next(dayOfWeek))
        .format(DEFAULT_FORMATTER);
  }

  @Override
  public String getPreviousWeekday(@NonNull String dateStr, @NonNull DayOfWeek dayOfWeek)
      throws DateTimeUtilException {
    // Validate date is not empty
    validateDateNotEmpty(dateStr);

    return parseDateInDefaultFormat(dateStr).with(TemporalAdjusters.previous(dayOfWeek))
        .format(DEFAULT_FORMATTER);
  }

  @Override
  public String getStartOfWeek(@NonNull String dateStr) throws DateTimeUtilException {
    // Validate date is not empty
    validateDateNotEmpty(dateStr);

    return parseDateInDefaultFormat(dateStr).with(
        TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).format(
        DEFAULT_FORMATTER);
  }

  @Override
  public String getEndOfWeek(@NonNull String dateStr) throws DateTimeUtilException {
    // Validate date is not empty
    validateDateNotEmpty(dateStr);

    return parseDateInDefaultFormat(dateStr).with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY))
        .format(
            DEFAULT_FORMATTER);
  }

  @Override
  public int calculateAge(@NonNull String birthDate) throws DateTimeUtilException {
    validateDateNotEmpty(birthDate);

    LocalDate birth = parseDateInDefaultFormat(birthDate);
    LocalDate today = LocalDate.now();

    if (birth.isAfter(today)) {
      throw new DateTimeUtilException(ErrorsEnum.INVALID_BIRTH_DATE.getMessage());
    }

    return Period.between(birth, LocalDate.now()).getYears();
  }

  @Override
  public boolean isSameDay(@NonNull String date1, @NonNull String date2)
      throws DateTimeUtilException {
    validateBothDatesNotEmpty(date1, date2);

    LocalDate d1 = parseDateInDefaultFormat(date1);
    LocalDate d2 = parseDateInDefaultFormat(date2);
    return d1.isEqual(d2);
  }

  @Override
  public boolean isSameMonth(@NonNull String date1, @NonNull String date2)
      throws DateTimeUtilException {
    validateBothDatesNotEmpty(date1, date2);

    LocalDate d1 = parseDateInDefaultFormat(date1);
    LocalDate d2 = parseDateInDefaultFormat(date2);

    return d1.getYear() == d2.getYear() && d1.getMonth() == d2.getMonth();
  }

  @Override
  public boolean isSameYear(@NonNull String date1, @NonNull String date2)
      throws DateTimeUtilException {
    validateBothDatesNotEmpty(date1, date2);

    LocalDate d1 = parseDateInDefaultFormat(date1);
    LocalDate d2 = parseDateInDefaultFormat(date2);
    return d1.getYear() == d2.getYear();
  }

  @Override
  public LocalDate convertStringToLocalDate(@NonNull String dateStr, @NonNull String format)
      throws DateTimeUtilException {
    validateDateNotEmpty(dateStr);

    if (!isValidFormats(format)) {
      throw new DateTimeUtilException(ErrorsEnum.INVALID_DESIRED_FORMAT.getMessage());
    }
    try {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
      return LocalDate.parse(dateStr, formatter);
    } catch (DateTimeParseException exception) {
      log.warn("Failed to parse date '{}' with format '{}'. Error: {}", dateStr, format,
          exception.getMessage(), exception);
      throw new DateTimeUtilException(ErrorsEnum.INVALID_DATE_FORMAT.getMessage());
    }
  }

  @Override
  public LocalDateTime convertStringToLocalDateTime(@NonNull String dateTimeStr,
      @NonNull String format) throws DateTimeUtilException {
    validateDateNotEmpty(dateTimeStr);

    if (!isValidFormats(format)) {
      throw new DateTimeUtilException(ErrorsEnum.INVALID_DESIRED_FORMAT.getMessage());
    }

    try {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
      return LocalDateTime.parse(dateTimeStr, formatter);
    } catch (DateTimeParseException exception) {
      log.warn("Failed to parse date time '{}' with format '{}'. Error: {}", dateTimeStr, format,
          exception.getMessage(), exception);
      throw new DateTimeUtilException(
          ErrorsEnum.INVALID_DATE_FORMAT.getMessage());
    }
  }

  @Override
  public String convertLocalDateToString(@NonNull LocalDate date, @NonNull String format)
      throws DateTimeUtilException {
    if (!isValidFormats(format)) {
      throw new DateTimeUtilException(ErrorsEnum.INVALID_DESIRED_FORMAT.getMessage());
    }
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
    return date.format(formatter);
  }

  @Override
  public String convertLocalDateTimeToString(@NonNull LocalDateTime dateTime,
      @NonNull String format) throws DateTimeUtilException {
    if (!isValidFormats(format)) {
      throw new DateTimeUtilException(ErrorsEnum.INVALID_DESIRED_FORMAT.getMessage());
    }

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
    return dateTime.format(formatter);
  }

  private LocalDate parseDateInDefaultFormat(final String dateStr) throws DateTimeUtilException {
    try {
      return LocalDate.parse(dateStr, DEFAULT_FORMATTER);

    } catch (DateTimeParseException exception) {
      log.warn("Failed to parse date '{}'. Error: {}", dateStr, exception);
      throw new DateTimeUtilException(
          ErrorsEnum.INVALID_DATE_FORMAT.getMessage());
    }
  }

  private void validateEndDateBeforeStartDate(LocalDate start, LocalDate end)
      throws DateTimeUtilException {
    if (start.isAfter(end)) {
      throw new DateTimeUtilException(ErrorsEnum.INVALID_END_BEFORE_START_DATE.getMessage());
    }
  }

  private void validateDateNotEmpty(@NonNull String date) throws DateTimeUtilException {
    if (Strings.isEmpty(date)) {
      throw new DateTimeUtilException(ErrorsEnum.INVALID_DATE_FORMAT.getMessage());
    }
  }

  private boolean isValidFormats(String format) {
    return format.equals(DateFormats.FORMAT_ISO_DATE)
        || format.equals(DateFormats.FORMAT_ISO_DATETIME)
        || format.equals(DateFormats.FORMAT_ISO_DATETIME_MILLIS_Z)
        || format.equals(DateFormats.FORMAT_ISO_DATETIME_MILLIS_X)
        || format.equals(DateFormats.FORMAT_CUSTOM_DD_MM_YYYY)
        || format.equals(DateFormats.FORMAT_CUSTOM_MM_DD_YYYY)
        || format.equals(DateFormats.FORMAT_CUSTOM_FULL_DATE)
        || format.equals(DateFormats.FORMAT_CUSTOM_DD_MMMM_YYYY)
        || format.equals(DateFormats.FORMAT_CUSTOM_DD_MMM_YYYY)
        || format.equals(DateFormats.FORMAT_TIME_ONLY_HH_MM_SS)
        || format.equals(DateFormats.FORMAT_TIME_ONLY_HH_MM_AM_PM)
        || format.equals(DateFormats.FORMAT_TIME_ONLY_HH_MM)
        || format.equals(DateFormats.FORMAT_DATETIME_DD_MM_HH_MM_SS)
        || format.equals(DateFormats.FORMAT_DATETIME_ISO)
        || format.equals(DateFormats.FORMAT_DATETIME_MM_DD_AM_PM)
        || format.equals(DateFormats.FORMAT_YEAR_WEEK)
        || format.equals(DateFormats.FORMAT_FULL_DATE_DAY_OF_WEEK)
        || format.equals(DateFormats.FORMAT_SHORT_DATE)
        || format.equals(DateFormats.FORMAT_DATETIME_WITH_TZ)
        || format.equals(DateFormats.FORMAT_BASIC_DATETIME)
        || format.equals(DateFormats.FORMAT_BASIC)
        || format.equals(DateFormats.FORMAT_MONTH_YEAR);
  }
}
