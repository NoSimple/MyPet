package com.example.mypetnews.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mypetnews.model.ResponseModel;
import com.example.mypetnews.model.Story;
import com.example.mypetnews.network.ErrorType;
import com.example.mypetnews.network.RetrofitService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public final class StoryViewModel extends ViewModel {

    private MutableLiveData<ResponseModel> liveData = new MutableLiveData<>();
    private ResponseModel responseModel = new ResponseModel();

    public LiveData<ResponseModel> getStoriesListLiveData(int id) {

        RetrofitService.getInstanceRepository().getStoryRequest(id).enqueue(new Callback<Story>() {

            @Override
            public void onResponse(@NotNull Call<Story> call, @NotNull Response<Story> response) {
                if (response.body() != null) {
                    responseModel.setStory(response.body());
                    responseModel.setErrorType(null);
                    liveData.setValue(responseModel);
                } else {
                    responseModel.setStories(null);
                    responseModel.setErrorType(ErrorType.EMPTY_DATA);
                    liveData.setValue(responseModel);
                }
            }

            @Override
            public void onFailure(@NotNull Call<Story> call, Throwable t) {
                responseModel.setStories(null);
                responseModel.setErrorType(ErrorType.NETWORK_CONNECTION);
                liveData.setValue(responseModel);
            }
        });

        return liveData;
    }
}