package com.example.databindingmvvmrespositoryjava.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databindingmvvmrespositoryjava.data.model.User;
import com.example.databindingmvvmrespositoryjava.databinding.ListItemBinding;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private final Context context;
    private List<User> userList;

    public UserAdapter(Context context) {
        this.context = context;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemBinding binding = ListItemBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        /*User user = userList.get(position);
//        holder.bind(user);
        holder.binding.setUserData(user);
        holder.binding.executePendingBindings();*/

        if (userList != null) {
            User user = userList.get(position);
            holder.binding.setUserData(user);
            holder.binding.executePendingBindings();
        }
    }

    @Override
    public int getItemCount() {
//        return userList.size();
        return userList != null ? userList.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final ListItemBinding binding;

        public ViewHolder(@NonNull ListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

       /* void bind(User data) {
            binding.setUserData(data);
            binding.executePendingBindings();
        }*/
    }
}