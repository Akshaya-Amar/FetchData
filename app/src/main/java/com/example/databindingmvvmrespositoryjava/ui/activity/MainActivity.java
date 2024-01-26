package com.example.databindingmvvmrespositoryjava.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.databindingmvvmrespositoryjava.R;
import com.example.databindingmvvmrespositoryjava.data.viewmodel.UserViewModel;
import com.example.databindingmvvmrespositoryjava.databinding.ActivityMainBinding;
import com.example.databindingmvvmrespositoryjava.ui.adapter.UserAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        UserViewModel viewModel = new ViewModelProvider(this).get(UserViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        UserAdapter adapter = new UserAdapter(this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(adapter);

        // Observe changes in user list
        viewModel.getUserList().observe(this, users -> adapter.setUserList(users));
    }
}