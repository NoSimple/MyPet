package com.example.mypetnews.util;

import android.app.Application;
import android.net.Network;

import androidx.constraintlayout.solver.Cache;

import com.example.mypetnews.R;

public final class MainApplication extends Application {

    //private RequestQueue mRequestQueue;

    public final String apiBaseUrl = "https://hex-api.herokuapp.com";

    public void onCreate() {
        super.onCreate();
        //setupAnalytics();
    }

    /*public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            int MEGABYTE = 1024 * 1024;
            Cache cache = new DiskBasedCache(getCacheDir(), MEGABYTE);
            Network network = new BasicNetwork(new HurlStack());
            mRequestQueue = new RequestQueue(cache, network);
            mRequestQueue.start();
        }

        return mRequestQueue;
    }*/

    /*private void setupAnalytics() {
        GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
        Tracker tracker = analytics.newTracker(R.xml.global_tracker);
        tracker.enableAutoActivityTracking(true);
        tracker.enableExceptionReporting(true);
    }*/
}