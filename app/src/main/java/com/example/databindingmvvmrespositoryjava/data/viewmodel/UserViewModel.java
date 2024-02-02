package com.example.databindingmvvmrespositoryjava.data.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.databindingmvvmrespositoryjava.data.model.User;
import com.example.databindingmvvmrespositoryjava.data.repository.UserRepository;

import java.util.List;

public class UserViewModel extends ViewModel {

    private final MutableLiveData<List<User>> userList = new MutableLiveData<>();
    private final UserRepository userRepository;
    private static final String TAG = UserViewModel.class.getSimpleName();

    public UserViewModel() {
        userRepository = new UserRepository();
        fetchUsers();
    }

    public LiveData<List<User>> getUserList() {
        return userList;
    }

    private void fetchUsers() {
        userRepository.getUsers(new UserRepository.UserCallback() {
            @Override
            public void onSuccess(List<User> users) {
                userList.setValue(users);
            }

            @Override
            public void onFailure(String errorMessage) {
                Log.i(TAG, "onFailure: " + errorMessage);
            }
        });
    }
}