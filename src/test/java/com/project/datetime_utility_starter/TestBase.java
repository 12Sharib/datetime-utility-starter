package com.project.datetime_utility_starter;

import com.project.datetime_utility_starter.utils.service.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestBase {
  @Autowired
  protected DateTimeUtils dateTimeUtils;
}
