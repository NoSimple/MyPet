package com.example.mypetnews.network;

import com.example.mypetnews.model.Story;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IRequestApi {

    @GET("stories/{path}")
    Call<List<Story>> getStoriesListApi(@Path("path") String path);

    @GET("story/{id}")
    Call<Story> getStoryApi(@Path("id") Integer path);
}