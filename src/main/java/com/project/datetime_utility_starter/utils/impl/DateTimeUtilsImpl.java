package com.project.datetime_utility_starter.utils.impl;

import com.project.datetime_utility_starter.utils.enums.ErrorsEnum;
import com.project.datetime_utility_starter.utils.exceptions.DateTimeUtilException;
import com.project.datetime_utility_starter.utils.formats.DateFormats;
import com.project.datetime_utility_starter.utils.service.DateTimeUtils;
import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.util.Strings;

@Log4j2
public class DateTimeUtilsImpl implements DateTimeUtils {


  @Override
  public String convertDateFormat(@NonNull String dateStr, @NonNull String desiredFormat)
      throws DateTimeUtilException {
    log.debug("Entry inside @class DateTimeUtilsImpl @method convertDateFormat");

    final List<String> inputFormats = getInBuildFormats();

    final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(desiredFormat);

    for (String inputFormat : inputFormats) {
      try {
        final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(inputFormat);

        if (inputFormat.contains("HH") || inputFormat.contains("mm") || inputFormat.contains(
            "ss")) {
          LocalDateTime parsedDateTime = LocalDateTime.parse(dateStr, inputFormatter);
          log.info("Parsed LocalDateTime: {}", parsedDateTime);

          String formattedDate = parsedDateTime.format(outputFormatter);
          log.info("Formatted DateTime: {}", formattedDate);
          return formattedDate;
        } else {
          // Parse as LocalDate for date-only formats
          LocalDate parsedDate = LocalDate.parse(dateStr, inputFormatter);
          log.info("Parsed LocalDate: {}", parsedDate);

          // Format and return (default time is added if needed)
          String formattedDate = parsedDate.atStartOfDay().format(outputFormatter);
          log.info("Formatted DateTime with default time: {}", formattedDate);
          return formattedDate;
        }
      } catch (Exception ignored) {
      }
    }
    log.error("Unable to parse date with any known format");
    throw new DateTimeUtilException("Invalid date or unsupported format: " + dateStr);
  }


  @Override
  public List<String> getDatesInRangeByUnit(
      @NonNull String startDate,
      @NonNull String endDate,
      @NonNull String unit,
      @NonNull String desiredFormat)
      throws DateTimeUtilException {

    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(desiredFormat);

    LocalDate start;
    LocalDate end;

    try {
      start = LocalDate.parse(startDate, formatter);
      end = LocalDate.parse(endDate, formatter);
    } catch (DateTimeParseException exception) {
      throw new DateTimeUtilException(
          ErrorsEnum.INVALID_DATE_FORMAT.getMessage() + desiredFormat);
    }

    if (start.isAfter(end)) {
      throw new DateTimeUtilException("Start date must not be after the end date.");
    }

    // Your logic for 'days', 'weeks', and 'months' goes here
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
      default -> throw new DateTimeUtilException("Invalid unit. Use 'days', 'weeks', or 'months'.");
    };
  }

  @Override
  public Long calculateBusinessDays(@NonNull final String startDate, @NonNull final String endDate)
      throws DateTimeUtilException {
    log.debug("Entry inside @class DateTimeUtilsImpl @method calculateBusinessDays");

    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateFormats.FORMAT_ISO_DATE);

    LocalDate start;
    LocalDate end;

    try {
      start = LocalDate.parse(startDate, formatter);
      end = LocalDate.parse(endDate, formatter);
    } catch (DateTimeParseException exception) {
      throw new DateTimeUtilException(
          ErrorsEnum.INVALID_DATE_FORMAT.getMessage());
    }

    if (start.isAfter(end)) {
      throw new DateTimeUtilException("Start date must not be after the end date.");
    }

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

    if (Strings.isEmpty(startDate) || Strings.isEmpty(endDate)) {
      throw new DateTimeUtilException("Invalid date format. Expected format is yyyy-MM-dd.");
    }

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate start;
    LocalDate end;
    try {
      start = LocalDate.parse(startDate, formatter);
      end = LocalDate.parse(endDate, formatter);
    } catch (DateTimeParseException e) {
      throw new DateTimeUtilException("Invalid date format. Expected format is yyyy-MM-dd.");
    }

    if (start.isAfter(end)) {
      throw new DateTimeUtilException("Start date cannot be after end date.");
    }

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

  //  Pending
  @Override
  public Long calculatePublicHolidays(@NonNull String startDate, @NonNull String endDate)
      throws DateTimeUtilException {
    log.debug("Entry inside @class DateTimeUtilsImpl @method calculatePublicHolidays");

    if (Strings.isEmpty(startDate) || Strings.isEmpty(endDate)) {
      throw new DateTimeUtilException("Invalid date format. Expected format is yyyy-MM-dd.");
    }

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate start;
    LocalDate end;
    try {
      start = LocalDate.parse(startDate, formatter);
      end = LocalDate.parse(endDate, formatter);
    } catch (DateTimeParseException e) {
      throw new DateTimeUtilException("Invalid date format. Expected format is yyyy-MM-dd.");
    }

    if (start.isAfter(end)) {
      throw new DateTimeUtilException("Start date cannot be after end date.");
    }
    return 0L;
  }

  @Override
  public Map<String, Long> calculateDaysMonthsYears(@NonNull String startDate,
      @NonNull String endDate)
      throws DateTimeUtilException {
    log.debug("Entry inside @class DateTimeUtilsImpl @method calculateDaysMonthsYears");

    if (Strings.isEmpty(startDate) || Strings.isEmpty(
        endDate)) {
      throw new DateTimeUtilException("Invalid date format. Expected format is yyyy-MM-dd.");
    }

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate start;
    LocalDate end;
    try {
      start = LocalDate.parse(startDate, formatter);
      end = LocalDate.parse(endDate, formatter);
    } catch (DateTimeParseException e) {
      throw new DateTimeUtilException("Invalid date format. Expected format is yyyy-MM-dd.");
    }

    if (start.isAfter(end)) {
      throw new DateTimeUtilException("Start date cannot be after end date.");
    }
    Period period = Period.between(start, end);
    long years = period.getYears();
    long months = period.getMonths();
    long days = period.getDays();

    Map<String, Long> result = new HashMap<>();
    result.put("years", years);
    result.put("months", months);
    result.put("days", days);
    return result;
  }

  @Override
  public Boolean isExistsInLeapYear(@NonNull String date) throws DateTimeUtilException {
    log.debug("Entry inside @class DateTimeUtilsImpl @method isExistsInLeapYear");

    if (Strings.isEmpty(date)) {
      throw new DateTimeUtilException("Invalid date format. Expected format is yyyy-MM-dd.");
    }
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate localDate;
    try {
      localDate = LocalDate.parse(date, formatter);
      if (!localDate.format(formatter).equals(date)) {
        throw new DateTimeUtilException("Invalid date, Provide valid date.");
      }
      return localDate.isLeapYear();
    } catch (DateTimeParseException e) {
      throw new DateTimeUtilException("Invalid date format. Expected format is yyyy-MM-dd.");
    }

  }



  @Override
  public String formatCurrentDate(@NonNull String desiredFormat) throws DateTimeUtilException {
    log.debug("Entry inside @class DateTimeUtilsImpl @method formatCurrentDate");

    if (!isValidFormat(desiredFormat)) {
      throw new DateTimeUtilException(
          "Invalid date format. Please use one of the predefined formats from the DateFormats class");
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

  private boolean isValidFormat(String format) {
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
        || format.equals(DateFormats.FORMAT_MONTH_YEAR);
  }

  private List<String> getInBuildFormats() {
    return Arrays.asList(
        DateFormats.FORMAT_CUSTOM_DD_MM_YYYY,
        DateFormats.FORMAT_CUSTOM_MM_DD_YYYY,
        DateFormats.FORMAT_ISO_DATE,
        DateFormats.FORMAT_DATETIME_ISO,
        DateFormats.FORMAT_BASIC,
        DateFormats.FORMAT_BASIC_DATETIME,
        DateFormats.FORMAT_CUSTOM_FULL_DATE,
        DateFormats.FORMAT_CUSTOM_DD_MMMM_YYYY,
        DateFormats.FORMAT_CUSTOM_DD_MMM_YYYY,
        DateFormats.FORMAT_TIME_ONLY_HH_MM_SS,
        DateFormats.FORMAT_TIME_ONLY_HH_MM_AM_PM,
        DateFormats.FORMAT_TIME_ONLY_HH_MM,
        DateFormats.FORMAT_DATETIME_DD_MM_HH_MM_SS,
        DateFormats.FORMAT_DATETIME_MM_DD_AM_PM,
        DateFormats.FORMAT_ISO_DATETIME,
        DateFormats.FORMAT_ISO_DATETIME_MILLIS_Z,
        DateFormats.FORMAT_ISO_DATETIME_MILLIS_X,
        DateFormats.FORMAT_YEAR_WEEK);
  }
}
