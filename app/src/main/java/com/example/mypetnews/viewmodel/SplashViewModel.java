package com.example.mypetnews.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mypetnews.model.ResponseModel;
import com.example.mypetnews.model.Story;
import com.example.mypetnews.network.ErrorType;
import com.example.mypetnews.network.RetrofitService;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public final class SplashViewModel extends ViewModel {

    private MutableLiveData<ResponseModel> liveData = new MutableLiveData<>();
    private ResponseModel responseModel = new ResponseModel();

    public LiveData<ResponseModel> getStoriesListLiveData(String pathName) {

        RetrofitService.getInstanceRepository().getStoriesListRequest(pathName).enqueue(new Callback<List<Story>>() {

            @Override
            public void onResponse(@NotNull Call<List<Story>> call, @NotNull Response<List<Story>> response) {
                if (response.body() != null) {
                    responseModel.setStories(response.body());
                    responseModel.setErrorType(null);
                    liveData.setValue(responseModel);
                } else {
                    responseModel.setStories(null);
                    responseModel.setErrorType(ErrorType.EMPTY_DATA);
                    liveData.setValue(responseModel);
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<Story>> call, Throwable t) {
                responseModel.setStories(null);
                responseModel.setErrorType(ErrorType.NETWORK_CONNECTION);
                liveData.setValue(responseModel);
            }
        });

        return liveData;
    }
}