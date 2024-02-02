package com.example.databindingmvvmrespositoryjava.ui.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(user -> Toast.makeText(MainActivity.this, user.getName(), Toast.LENGTH_SHORT).show());

        // Observe changes in user list
        /*viewModel.getUserList().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                adapter.setUserList(users);
            }
        });*/

//        viewModel.getUserList().observe(this, users -> adapter.setUserList(users)); // same functionality, but used lambda expression
        viewModel.getUserList().observe(this, adapter::setUserList); // more concise code
    }
}