package com.example.mypetnews.view;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.mypetnews.R;
import com.example.mypetnews.BuildConfig;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.Calendar;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class AboutActivity extends AppCompatActivity {

    @BindView(R.id.tool_bar)
    protected Toolbar toolBar;

    @BindView(R.id.text_version_name)
    protected TextView versionNameText;

    @BindView(R.id.text_version_code)
    protected TextView versionCodeText;

    @BindView(R.id.text_year)
    protected TextView yearText;

    @BindView(R.id.ad_banner_view)
    protected AdView adBannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ButterKnife.bind(this);
        initToolbar();
        initAdBanner();

        versionNameText.setText(getResources().getText(R.string.version_name_text) + " " + BuildConfig.VERSION_NAME);
        versionCodeText.setText(getResources().getText(R.string.version_code_text) + " " + BuildConfig.VERSION_CODE);
        yearText.setText(Integer.toString(Calendar.getInstance().get(Calendar.YEAR)));
    }

    private void initToolbar() {
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.about_title_text);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }

    private void initAdBanner() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        adBannerView.loadAd(adRequest);

        InterstitialAd interstitialAd;
        interstitialAd = new InterstitialAd(Objects.requireNonNull(this));
        interstitialAd.setAdUnitId(getString(R.string.banner_ad_id));
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                finish();
            }
        });
    }
}