package com.example.mypetnews.util;

import android.text.format.DateUtils;

import java.util.Date;

public final class Constants {

    private Constants() {
    }

    public static final String BASE_URL = "http://api.hexforhn.com/";
    public static final String IMAGE_URL = "https://logo.clearbit.com/";
    public static final String IMAGE_SIZE = "?size=256&format=png";

    public static final String RESPONSE = "response";
    public static final String PARAMETER_REQUEST = "parameter";
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String STORY = "story";
    public static final String ERROR = "error";

    public static final String GET_PREFERENCE = "preference";
    public static final String GET_STORY = "story";

    public static String getRelativeTime(Date date) {
        return DateUtils.getRelativeTimeSpanString(date.getTime(), System.currentTimeMillis(), 0)
                .toString();
    }
}