package com.example.mypetnews.data.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface StoryDao {

    @Query("SELECT * FROM storyentity")
    StoryEntity getStory();

    @Insert
    void insertStory(StoryEntity story);

    @Query("DELETE FROM storyentity")
    void deleteStory();
}