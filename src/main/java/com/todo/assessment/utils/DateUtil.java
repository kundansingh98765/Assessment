package com.todo.assessment.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static String convertToStringDate(LocalDateTime localDateTime, String formatString) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatString);
        String formattedDateTime = localDateTime.format(dateTimeFormatter);
        return formattedDateTime;
    }
}
