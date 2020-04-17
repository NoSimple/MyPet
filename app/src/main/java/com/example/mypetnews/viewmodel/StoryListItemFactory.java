package com.example.mypetnews.viewmodel;

import android.content.ClipData;

import com.example.mypetnews.model.Story;

import java.util.ArrayList;
import java.util.List;

public final class StoryListItemFactory {

    public static ArrayList<StoryListItemViewModel> createItemListItems(List<? extends ClipData.Item> items) {
        ArrayList<StoryListItemViewModel> itemListItems = new ArrayList<>();

        if (items == null) {
            return itemListItems;
        }

        for (Object item : items) {
            if (item instanceof Story) {
                //itemListItems.add(createItemListItemForStory((Story) item));
            }
        }

        return itemListItems;
    }

    /*private static StoryListItemViewModel createItemListItemForStory(Story story) {
        return new StoryListItemViewModel(story.getTitle(), story.getDomain(),
                story.getScore(), story.getCommentCount(), story.getDate());
    }*/
}