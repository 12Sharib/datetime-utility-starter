package com.project.datetime_utility_starter.utils.impl;


import com.project.datetime_utility_starter.TestBase;
import com.project.datetime_utility_starter.utils.exceptions.DateTimeUtilException;
import com.project.datetime_utility_starter.utils.formats.DateFormats;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
class DateTimeUtilsImplTest extends TestBase {
  @Test
  void convertDateFormat() throws DateTimeUtilException {
    String response = dateTimeUtils.convertDateFormat("2024/01-01", DateFormats.FORMAT_ISO_DATETIME);

    System.out.println(response);
  }
}
