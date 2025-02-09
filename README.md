# DateTime Utility Starter

A custom Spring Boot starter for handling various Date and Time operations efficiently. This library provides a set of utilities and annotations for parsing, formatting, and manipulating dates with ease.

## Features

- **GetDatesByUnit** – Retrieve dates based on a given unit (e.g., days, months, years).
- **GetCurrentDateInDesiredFormat** – Get the current date in a specified format.
- **CalculateBusinessDays** – Calculate business days between two dates, excluding weekends and holidays.
- **CalculateWeekdays** – Calculate the number of weekdays between two dates.
- **CalculateDaysMonthsYears** – Calculate the difference in days, months, or years between two dates.
- **IsExistsLeapYear** – Check if a given year is a leap year.
- **ConvertDateFormat** – Convert a date from one format to another.
- **Annotations:**
    - `@CurrentDateTime` – Automatically injects the current datetime into a field or parameter.
    - `@CurrentDate` – Automatically injects the current date into a field or parameter.
- **AddDaysToDate** – Add a specified number of days to a given date.
- **SubtractDaysFromDate** – Subtract a specified number of days from a given date.
- **AddMonthsToDate** – Add a specified number of months to a given date.
- **AddYearsToDate** – Add a specified number of years to a given date.
- **GetFirstAndLastDayOfMonth** – Retrieve the first and last day of a given month.
- **GetFirstAndLastDayOfYear** – Retrieve the first and last day of a given year.
- **GetNextWeekday** – Get the next occurrence of a specified weekday (e.g., next Monday from today).
- **GetPreviousWeekday** – Get the last occurrence of a specified weekday.
- **GetStartAndEndOfWeek** – Get the start (Monday) and end (Sunday) of a given week.
- **CalculateAge** – Calculate age based on a birthdate.
- **IsSameDay** – Check if two dates represent the same day.
- **IsSameMonth** – Check if two dates fall in the same month.
- **IsSameYear** – Check if two dates fall in the same year.
- **ConvertStringToDate** – Convert a string into a `LocalDate` or `LocalDateTime` based on a given pattern.
- **ConvertDateToString** – Convert a `LocalDate` or `LocalDateTime` to a string in a given format.
- **ConvertLocalDateTimeToString** – Convert `LocalDateTime` to a string.
- **ConvertLocalDateToString** – Convert `LocalDate` to a string.
- **ConvertStringToLocalDateTime** – Convert a string to `LocalDateTime` using a specified format.
- **ConvertStringToLocalDate** – Convert a string to `LocalDate` using a specified format.

## 📦 Installation

To use this starter in your Spring Boot project, add the following dependency:

```xml
<dependency>
    <groupId>com.github.12Sharib</groupId>
    <artifactId>datetime-utility-starter</artifactId>
    <version>1.0.0</version>
</dependency>
```
## 🔗 Links

- **Source Code: GitHub Repo**(https://github.com/12Sharib/datetime-utility-starter)
- **GitHub Package: Maven Package**(https://maven.pkg.github.com/12Sharib/datetime-utility-starter)

## License

This project is open-source and available for personal and commercial use.

---

**Created by [Mohd Sharib Saifi]** – A fully functional DateTime Utility for Spring Boot projects.