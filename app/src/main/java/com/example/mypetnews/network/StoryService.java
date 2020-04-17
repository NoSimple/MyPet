package com.example.mypetnews.network;

import com.example.mypetnews.model.Story;

import org.json.JSONObject;

public final class StoryService {

    /*private final RequestQueue mRequestQueue;
    private final String mApiBaseUrl;

    public StoryService(RequestQueue requestQueue, String apiUrl) {
        this.mRequestQueue = requestQueue;
        mApiBaseUrl = apiUrl;
    }

    public Story getStory(String storyId) {
        String STORY_PATH = "/story";
        String storyPath = mApiBaseUrl + STORY_PATH + "/" + storyId;

        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        JsonObjectRequest request = new JsonObjectRequest(storyPath, future, future);
        request.setRetryPolicy(RetryPolicyFactory.build());
        mRequestQueue.add(request);

        try {
            JSONObject response = future.get();
            return StoryMarshaller.marshall(response);
        } catch (Exception e) {
            return null;
        }
    }*/
}