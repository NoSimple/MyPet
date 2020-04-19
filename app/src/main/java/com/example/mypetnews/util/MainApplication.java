package com.example.mypetnews.util;

import android.app.Application;

import com.example.mypetnews.R;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

public final class MainApplication extends Application {

    private Tracker tracker;

    public void onCreate() {
        super.onCreate();
        setupAnalytics();
    }

    private void setupAnalytics() {
        GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
        if (tracker == null) {
            tracker = analytics.newTracker(R.xml.global_tracker);
        }
        tracker.enableAutoActivityTracking(true);
        tracker.enableExceptionReporting(true);
    }
}