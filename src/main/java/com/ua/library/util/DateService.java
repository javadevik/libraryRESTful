package com.ua.library.util;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class DateService {
    private final SimpleDateFormat DATA_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public java.sql.Timestamp parseToTimestamp(String timestamp) {
        try {
            return new java.sql.Timestamp(DATA_TIME_FORMAT.parse(timestamp).getTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public Timestamp getCurrentDate() {
        return new Timestamp(System.currentTimeMillis());
    }
}
