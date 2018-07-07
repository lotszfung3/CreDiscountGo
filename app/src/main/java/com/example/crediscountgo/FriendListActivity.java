package com.example.crediscountgo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class FriendListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);

        RecyclerView friendListRecyclerView = (RecyclerView) findViewById(R.id.friend_list_recycler_view);
        FriendAdapter friendAdapter = new FriendAdapter(Friend.generateSampleFriendList());
        friendListRecyclerView.setAdapter(friendAdapter);
        friendListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
