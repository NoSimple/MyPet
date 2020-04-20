package com.example.mypetnews.data.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {StoryEntity.class}, version = 1)
public abstract class MainDataBase extends RoomDatabase {
    public abstract StoryDao storyDao();
}