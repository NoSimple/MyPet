package com.example.mypetnews.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mypetnews.model.Story;
import com.example.mypetnews.util.Constants;
import com.google.gson.Gson;

import static android.content.Context.MODE_PRIVATE;

public final class DataPreferences {

    public static void setRecipePreference(Context context, Story story) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.GET_PREFERENCE, MODE_PRIVATE);

        if (sharedPreferences != null) {
            sharedPreferences.edit().clear().apply();
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constants.GET_STORY, new Gson().toJson(story)).apply();
    }

    public static Story getRecipePreference(Context context) {

        SharedPreferences sharedPref = context.getSharedPreferences(Constants.GET_PREFERENCE, MODE_PRIVATE);
        String recipeJSON = sharedPref.getString(Constants.GET_STORY, "");
        return new Gson().fromJson(recipeJSON, Story.class);
    }
}