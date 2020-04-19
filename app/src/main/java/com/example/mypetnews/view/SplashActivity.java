package com.example.mypetnews.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mypetnews.R;
import com.example.mypetnews.model.ResponseModel;
import com.example.mypetnews.model.Story;
import com.example.mypetnews.network.ErrorType;
import com.example.mypetnews.network.PathType;
import com.example.mypetnews.network.RetrofitService;
import com.example.mypetnews.util.Constants;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {

    private AppCompatActivity activity = this;

    private SplashTask splashTask;

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

        connectButton.setOnClickListener(view -> {
            splashTask = new SplashTask();
            splashTask.execute();
        });

        splashTask = new SplashTask();
        splashTask.execute();
    }

    class SplashTask extends AsyncTask<Void, ResponseModel, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... params) {

            ResponseModel responseModel = new ResponseModel();
            RetrofitService.getInstanceRepository().getStoriesListRequest(PathType.TOP.getPathName()).enqueue(new Callback<List<Story>>() {

                @Override
                public void onResponse(@NotNull Call<List<Story>> call, @NotNull Response<List<Story>> response) {
                    if (response.body() != null) {
                        responseModel.setStories(response.body());
                        responseModel.setErrorType(null);
                        publishProgress(responseModel);
                    } else {
                        responseModel.setStories(null);
                        responseModel.setErrorType(ErrorType.EMPTY_DATA);
                        publishProgress(responseModel);
                    }
                }

                @Override
                public void onFailure(@NotNull Call<List<Story>> call, Throwable t) {
                    responseModel.setStories(null);
                    responseModel.setErrorType(ErrorType.NETWORK_CONNECTION);
                    publishProgress(responseModel);
                }
            });

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }

        @Override
        protected void onProgressUpdate(ResponseModel... values) {
            super.onProgressUpdate(values);

            if (values[0].getStories() != null) {
                Intent intent = new Intent(activity, MainActivity.class);
                intent.putParcelableArrayListExtra(Constants.RESPONSE, (ArrayList<? extends Parcelable>) values[0].getStories());
                startActivity(intent);
            } else {
                titleText.setVisibility(View.GONE);
                errorText.setText(values[0].getErrorType().getErrorName());
                errorBlock.setVisibility(View.VISIBLE);
            }

            progress.setVisibility(View.GONE);
        }
    }
}