package com.example.mypetnews.network;

public final class RetryPolicyFactory {

    private static final int SOCKET_TIMEOUT_MS = 2500;
    private static final int MAX_RETRIES = 3;
    private static final int BACKOFF_MULTIPLIER = 2;

    /*public static RetryPolicy build() {
        return new DefaultRetryPolicy(SOCKET_TIMEOUT_MS, MAX_RETRIES, BACKOFF_MULTIPLIER);
    }*/
}