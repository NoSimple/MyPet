package com.example.mypetnews.util;

import androidx.room.TypeConverter;

import java.util.Date;

public final class ConverterDate {

    @TypeConverter
    public static Date getDate(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTime(Date date) {
        return date == null ? null : date.getTime();
    }
}