package com.example.mypetnews.network;

import com.example.mypetnews.model.Story;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public final class FrontPageMarshaller {

    public static List<Story> marshall(JSONArray rawStories) {
        List<Story> frontPageStories = new ArrayList<>();

        for (int i = 0; i < rawStories.length(); i++) {
            try {
                //frontPageStories.add(StoryMarshaller.marshall(rawStories.getJSONObject(i)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return frontPageStories;
    }
}