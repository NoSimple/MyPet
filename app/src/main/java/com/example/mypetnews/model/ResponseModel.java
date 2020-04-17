package com.example.mypetnews.model;

import com.example.mypetnews.network.ErrorType;

import java.util.List;

public final class ResponseModel {

    private List<Story> stories = null;
    private ErrorType errorType = null;

    public List<Story> getStories() {
        return stories;
    }

    public void setStories(List<Story> stories) {
        this.stories = stories;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(ErrorType textError) {
        this.errorType = textError;
    }
}