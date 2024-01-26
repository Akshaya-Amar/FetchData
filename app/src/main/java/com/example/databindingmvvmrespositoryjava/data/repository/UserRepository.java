package com.example.databindingmvvmrespositoryjava.data.repository;

import android.util.Log;

import com.example.databindingmvvmrespositoryjava.data.api.ApiService;
import com.example.databindingmvvmrespositoryjava.data.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserRepository {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private final ApiService apiService;

    public interface UserCallback {
        void onSuccess(List<User> users);

        void onFailure(String errorMessage);
    }

    public UserRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public void getUsers(UserCallback callback) {
        // Perform API call using Retrofit
        Call<List<User>> call = apiService.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                callback.onFailure("Error: " + t.getMessage());
            }
        });
    }
}
