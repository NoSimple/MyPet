package com.example.mypetnews.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mypetnews.R;
import com.example.mypetnews.network.PathType;
import com.example.mypetnews.util.Constants;
import com.example.mypetnews.viewmodel.SplashViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    private SplashViewModel splashViewModel;

    @BindView(R.id.text_title)
    protected TextView titleText;

    @BindView(R.id.text_error)
    protected TextView errorText;

    @BindView(R.id.progress)
    protected ProgressBar progress;

    @BindView(R.id.block_error)
    protected LinearLayout errorBlock;

    @BindView(R.id.button_connect)
    protected Button connectButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ButterKnife.bind(this);
        splashViewModel = new ViewModelProvider(this).get(SplashViewModel.class);

        connectButton.setOnClickListener(view -> {
            progress.setVisibility(View.VISIBLE);
            request();
        });

        request();
    }

    private void request() {

        splashViewModel.getStoriesListLiveData(PathType.TOP.getPathName()).observe(this, response -> {

            if (response.getStories() != null) {
                progress.setVisibility(View.GONE);

                Intent intent = new Intent(this, MainActivity.class);
                intent.putParcelableArrayListExtra(Constants.RESPONSE, (ArrayList<? extends Parcelable>) response.getStories());
                startActivity(intent);
            } else {
                titleText.setVisibility(View.GONE);
                errorText.setText(response.getErrorType().getErrorName());
                errorBlock.setVisibility(View.VISIBLE);
                progress.setVisibility(View.GONE);
            }
        });
    }
}