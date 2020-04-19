package com.example.mypetnews.network;

public enum PathType {

    TOP("Top"),
    NEW("New"),
    ASK("Ask"),
    SHOW("Show"),
    JOBS("Jobs");

    private String pathName;

    PathType(String pathName) {
        this.pathName = pathName;
    }

    public String getPathName() {
        return pathName;
    }
}