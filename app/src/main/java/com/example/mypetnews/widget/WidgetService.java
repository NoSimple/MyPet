package com.example.mypetnews.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

public final class WidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new WidgetRemoteViewsFactory(getApplicationContext());
    }
}