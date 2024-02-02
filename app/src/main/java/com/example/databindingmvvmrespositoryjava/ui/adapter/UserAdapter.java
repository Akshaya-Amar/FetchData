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
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClicked(User user);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public UserAdapter(Context context) {
        this.context = context;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
        notifyItemInserted(userList.size() - 1);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemBinding binding = ListItemBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ViewHolder(binding, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (userList != null) {
            User user = userList.get(position);
            holder.binding.setUser(user);
            holder.binding.executePendingBindings();
        }
    }

    @Override
    public int getItemCount() {
        return userList != null ? userList.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final ListItemBinding binding;

        public ViewHolder(ListItemBinding binding, OnItemClickListener onItemClickListener) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(view -> {

                if (onItemClickListener != null) {

                    int position = getAdapterPosition();

                    if (position != RecyclerView.NO_POSITION) {
                        onItemClickListener.onItemClicked(binding.getUser());
                    }
                }
            });
        }
    }
}