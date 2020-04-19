package com.example.mypetnews.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

public final class CustomWebView extends WebView {

    private OnScrollChangedCallback onScrollChangedCallback;

    public CustomWebView(final Context context) {
        super(context);
    }

    public CustomWebView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomWebView(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onScrollChanged(final int scrollX, final int scrollY, final int oldScrollX,
                                   final int oldScrollY) {
        super.onScrollChanged(scrollX, scrollY, oldScrollX, oldScrollY);
        if (onScrollChangedCallback != null) {
            onScrollChangedCallback.onScroll(scrollX, scrollY);
        }
    }

    public void setOnScrollChangedCallback(final OnScrollChangedCallback onScrollChangedCallback) {
        this.onScrollChangedCallback = onScrollChangedCallback;
    }

    public interface OnScrollChangedCallback {
        void onScroll(int scrollX, int scrollY);
    }
}