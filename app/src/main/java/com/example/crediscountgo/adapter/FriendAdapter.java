package com.example.crediscountgo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.crediscountgo.R;
import com.example.crediscountgo.model.Friend;

import java.util.List;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView iconImageView;
        public TextView nameTextView;
        public TextView descriptionTextView;

        public ViewHolder(View itemView){
            super(itemView);

            iconImageView = (ImageView) itemView.findViewById(R.id.friend_item_icon);
            nameTextView = (TextView) itemView.findViewById(R.id.friend_item_name);
            descriptionTextView = (TextView) itemView.findViewById(R.id.friend_item_description);
        }
    }

    private List<Friend> friends;

    public FriendAdapter(List<Friend> friends){
        this.friends = friends;
    }

    @Override
    public int getItemCount() {
        return friends.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View contactView = LayoutInflater.from(context).inflate(R.layout.item_friend, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Friend friend = friends.get(position);
        ImageView iconImageView = holder.iconImageView;
        TextView nameTextView = holder.nameTextView;
        TextView descriptionTextView = holder.descriptionTextView;

        iconImageView.setBackgroundResource(friend.getIcon());
        nameTextView.setText(friend.getName());
        descriptionTextView.setText(friend.getDescription());
    }
}
