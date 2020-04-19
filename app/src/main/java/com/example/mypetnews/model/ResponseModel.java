package com.example.mypetnews.model;

import com.example.mypetnews.network.ErrorType;

import java.util.List;

public final class ResponseModel {

    private List<Story> stories = null;

    private Story story = null;
    private ErrorType errorType = null;

    public List<Story> getStories() {
        return stories;
    }

    public void setStories(List<Story> stories) {
        this.stories = stories;
    }

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(ErrorType textError) {
        this.errorType = textError;
    }
}