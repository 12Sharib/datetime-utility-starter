package com.project.datetime_utility_starter.utils.service;

import com.project.datetime_utility_starter.utils.exceptions.DateTimeUtilException;
import java.util.List;
import java.util.Map;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public interface DateTimeUtils {

  String convertDateFormat(String dateStr, String desiredFormat) throws DateTimeUtilException;

  String formatCurrentDate(@NonNull String desiredFormat) throws DateTimeUtilException;

  List<String> getDatesInRangeByUnit(
      @NonNull String startDate, @NonNull String endDate, @NonNull String unit,
      @NonNull String desiredFormat)
      throws DateTimeUtilException;

  Long calculateBusinessDays(@NonNull String startDate, @NonNull String endDate)
      throws DateTimeUtilException;

  Long calculateWeekends(@NonNull String startDate, @NonNull String endDate)
      throws DateTimeUtilException;

  Long calculatePublicHolidays(@NonNull String startDate, @NonNull String endDate)
      throws DateTimeUtilException;

  Map<String, Long> calculateDaysMonthsYears(@NonNull String startDate, @NonNull String endDate)
      throws DateTimeUtilException;

  Boolean isExistsInLeapYear(@NonNull String date) throws DateTimeUtilException;
}
