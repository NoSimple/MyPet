package com.example.mypetnews.util;

import android.app.Application;

import androidx.room.Room;

import com.example.mypetnews.R;
import com.example.mypetnews.data.room.MainDataBase;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

public final class MainApplication extends Application {

    private static MainApplication application;

    private MainDataBase dataBase;
    private Tracker tracker;

    public void onCreate() {
        super.onCreate();

        application = this;
        setupAnalytics();
        dataBase = Room.databaseBuilder(this, MainDataBase.class, "MainDB").build();
    }

    private void setupAnalytics() {
        GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
        if (tracker == null) {
            tracker = analytics.newTracker(R.xml.global_tracker);
        }
        tracker.enableAutoActivityTracking(true);
        tracker.enableExceptionReporting(true);
    }

    public static MainApplication getMainApplication() {
        return application;
    }

    public MainDataBase getDataBase() {
        return dataBase;
    }
}