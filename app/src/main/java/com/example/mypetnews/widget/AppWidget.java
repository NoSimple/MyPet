package com.example.mypetnews.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import com.example.mypetnews.R;

public final class AppWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {

        Intent serviceIntent = new Intent(context, WidgetService.class);
        serviceIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        serviceIntent.setData(Uri.parse(serviceIntent.toUri(Intent.URI_INTENT_SCHEME)));

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_story);
        views.setRemoteAdapter(R.id.widget_stories_list, serviceIntent);
        views.setEmptyView(R.id.widget_stories_list, R.id.widget_stories_empty);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
    }

    @Override
    public void onDisabled(Context context) {
    }
}