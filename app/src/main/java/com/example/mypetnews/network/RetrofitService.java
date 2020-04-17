package com.example.mypetnews.network;

import com.example.mypetnews.model.Story;
import com.example.mypetnews.util.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RetrofitService {

    private static RetrofitService retrofitService;
    private Retrofit retrofit;

    public static RetrofitService getInstanceRepository() {
        if (retrofitService == null) {
            retrofitService = new RetrofitService();
        }
        return retrofitService;
    }

    private RetrofitService() {

        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Call<List<Story>> getStoriesListRequest(String path) {
        return retrofit.create(IRequestApi.class).getStoriesListApi(path);
    }

    public Call<Story> getStoryRequest(int storyId) {
        return retrofit.create(IRequestApi.class).getStoryApi(storyId);
    }
}