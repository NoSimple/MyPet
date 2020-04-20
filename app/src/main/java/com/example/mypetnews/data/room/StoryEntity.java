package com.example.mypetnews.data.room;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class StoryEntity {

    @NonNull
    @PrimaryKey
    public Integer id;

    public String title;

    public String author;

    public Integer commentCount;

    public String domain;

    public Long time;

    public Integer score;
}