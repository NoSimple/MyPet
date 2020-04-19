package com.example.mypetnews.widget;

import android.content.Context;
import android.net.Uri;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.mypetnews.R;
import com.example.mypetnews.data.DataPreferences;
import com.example.mypetnews.model.Story;
import com.example.mypetnews.util.Constants;

import java.util.ArrayList;
import java.util.List;

public final class WidgetRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private Story story;
    private List<Story> storiesList = new ArrayList<>();
    private Context context;

    public WidgetRemoteViewsFactory(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDataSetChanged() {
        story = DataPreferences.getRecipePreference(context);
        storiesList.clear();
        storiesList.add(story);
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public int getCount() {
        return storiesList.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {

        final RemoteViews remoteView = new RemoteViews(context.getPackageName(), R.layout.widget_story_item);
        remoteView.setImageViewUri(R.id.image_story, Uri.parse(Constants.IMAGE_URL + story.getDomain() + Constants.IMAGE_SIZE));
        remoteView.setTextViewText(R.id.text_score, story.getScore().toString());
        remoteView.setTextViewText(R.id.text_comment_count, story.getCommentCount().toString());
        remoteView.setTextViewText(R.id.text_source, story.getDomain());
        remoteView.setTextViewText(R.id.text_time, Constants.getRelativeTime(story.getTime()));
        remoteView.setTextViewText(R.id.text_title, story.getTitle());

        return remoteView;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}