package com.example.mypetnews.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.mypetnews.R;
import com.example.mypetnews.custom.CustomWebView;
import com.example.mypetnews.model.Story;
import com.example.mypetnews.util.Constants;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class NewsFragment extends Fragment {

    private int storyId;
    private Story story;
    private String error;

    @BindView(R.id.refresh_news)
    protected SwipeRefreshLayout newsRefresh;

    @BindView(R.id.web_view_news)
    protected CustomWebView newsWebView;

    @BindView(R.id.fab_share)
    protected FloatingActionButton shareFab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            storyId = bundle.getInt(Constants.ID);
            story = bundle.getParcelable(Constants.STORY);
            error = bundle.getString(Constants.ERROR);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRefresh();
        setupWebView();

        shareFab.setOnClickListener(v -> {
            handleShare();
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState == null) {
            newsWebView.loadUrl(story.getUrl());
        } else {
            newsWebView.restoreState(savedInstanceState);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ((StoryActivity) getActivity()).setNewsFragment(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        newsWebView.saveState(outState);
    }

    private WebView setupWebView() {

        newsWebView.setWebViewClient(new WebViewClient());
        newsWebView.getSettings().setLoadWithOverviewMode(true);
        newsWebView.getSettings().setUseWideViewPort(true);
        newsWebView.getSettings().setBuiltInZoomControls(true);
        newsWebView.getSettings().setDisplayZoomControls(false);
        newsWebView.getSettings().setJavaScriptEnabled(true);
        newsWebView.setWebViewClient(new WebViewClient() {

            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                newsRefresh.setRefreshing(true);
            }

            public void onPageFinished(WebView view, String url) {
                newsRefresh.setRefreshing(false);
            }

            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                if (!request.isForMainFrame()) {
                    return;
                }

                newsRefresh.setRefreshing(false);
            }
        });

        return newsWebView;
    }

    private void setupRefresh() {
        newsRefresh.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        newsRefresh.setEnabled(false);
    }

    boolean handleBack() {
        if (newsWebView != null && newsWebView.canGoBack()) {
            newsWebView.goBack();
            return true;
        }
        return false;
    }

    private void handleShare() {

        String intentMessage = getString(R.string.share_text);
        String url = story.getUrl();
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, url);
        startActivity(Intent.createChooser(shareIntent, intentMessage));
    }
}