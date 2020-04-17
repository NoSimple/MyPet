package com.example.mypetnews.network;

public enum PathType {

    TOP("top"),
    NEW("new"),
    ASK("ask"),
    SHOW("show"),
    JOBS("jobs");

    private String pathName;

    PathType(String pathName) {
        this.pathName = pathName;
    }

    public String getPathName() {
        return pathName;
    }
}