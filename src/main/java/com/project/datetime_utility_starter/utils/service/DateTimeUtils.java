package com.project.datetime_utility_starter.utils.service;

import com.project.datetime_utility_starter.utils.exceptions.DateTimeUtilException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import lombok.NonNull;
import org.springframework.stereotype.Service;

/**
 * @author Mohd Sharib Saifi
 * @version 1.0.0
 */
@Service
public interface DateTimeUtils {

  String formatCurrentDate(@NonNull String desiredFormat) throws DateTimeUtilException;

  List<String> getDatesInRangeByUnit(
      @NonNull String startDate, @NonNull String endDate, @NonNull String unit,
      @NonNull String desiredFormat)
      throws DateTimeUtilException;

  Long calculateBusinessDays(@NonNull String startDate, @NonNull String endDate)
      throws DateTimeUtilException;

  Long calculateWeekends(@NonNull String startDate, @NonNull String endDate)
      throws DateTimeUtilException;

  Map<String, Long> calculateDaysMonthsYears(@NonNull String startDate, @NonNull String endDate)
      throws DateTimeUtilException;

  Boolean isExistsInLeapYear(@NonNull String date) throws DateTimeUtilException;

  String addDaysToDate(@NonNull String dateStr, int days) throws DateTimeUtilException;

  String subtractDaysFromDate(@NonNull String dateStr, int days) throws DateTimeUtilException;

  String addMonthsToDate(@NonNull String dateStr, int months) throws DateTimeUtilException;

  String addYearsToDate(@NonNull String dateStr, int years) throws DateTimeUtilException;

  String getFirstDayOfMonth(@NonNull String dateStr) throws DateTimeUtilException;

  String getLastDayOfMonth(@NonNull String dateStr) throws DateTimeUtilException;

  String getFirstDayOfYear(@NonNull String dateStr) throws DateTimeUtilException;

  String getLastDayOfYear(@NonNull String dateStr) throws DateTimeUtilException;

  String getNextWeekday(@NonNull String dateStr, @NonNull DayOfWeek dayOfWeek)
      throws DateTimeUtilException;

  String getPreviousWeekday(@NonNull String dateStr, @NonNull DayOfWeek dayOfWeek)
      throws DateTimeUtilException;

  String getStartOfWeek(@NonNull String dateStr) throws DateTimeUtilException;

  String getEndOfWeek(@NonNull String dateStr) throws DateTimeUtilException;

  int calculateAge(@NonNull String birthDate) throws DateTimeUtilException;

  boolean isSameDay(@NonNull String date1, @NonNull String date2) throws DateTimeUtilException;

  boolean isSameMonth(@NonNull String date1, @NonNull String date2) throws DateTimeUtilException;

  boolean isSameYear(@NonNull String date1, @NonNull String date2) throws DateTimeUtilException;

  LocalDate convertStringToLocalDate(@NonNull String dateStr, @NonNull String format) throws DateTimeUtilException;

  LocalDateTime convertStringToLocalDateTime(@NonNull String dateTimeStr, @NonNull String format) throws DateTimeUtilException;

  String convertLocalDateToString(@NonNull LocalDate date, @NonNull String format)
      throws DateTimeUtilException;

  String convertLocalDateTimeToString(@NonNull LocalDateTime dateTime, @NonNull String format)
      throws DateTimeUtilException;
}
