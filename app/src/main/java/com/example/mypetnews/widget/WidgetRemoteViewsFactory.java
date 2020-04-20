package com.example.mypetnews.widget;

import android.content.Context;
import android.net.Uri;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.mypetnews.R;
import com.example.mypetnews.data.room.StoryDao;
import com.example.mypetnews.data.room.StoryEntity;
import com.example.mypetnews.util.Constants;
import com.example.mypetnews.util.ConverterDate;
import com.example.mypetnews.util.MainApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public final class WidgetRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private StoryEntity story;
    private List<StoryEntity> storiesList = new ArrayList<>();
    private Context context;

    public WidgetRemoteViewsFactory(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDataSetChanged() {

        Executors.newSingleThreadExecutor().execute(() -> {
            StoryDao storyDao = MainApplication.getMainApplication().getDataBase().storyDao();
            story = storyDao.getStory();
        });

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
        remoteView.setImageViewUri(R.id.image_story, Uri.parse(Constants.IMAGE_URL + story.domain + Constants.IMAGE_SIZE));
        remoteView.setTextViewText(R.id.text_score, story.score.toString());
        remoteView.setTextViewText(R.id.text_comment_count, story.commentCount.toString());
        remoteView.setTextViewText(R.id.text_source, story.domain);
        remoteView.setTextViewText(R.id.text_time, Constants.getRelativeTime(ConverterDate.getDate(story.time)));
        remoteView.setTextViewText(R.id.text_title, story.title);

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