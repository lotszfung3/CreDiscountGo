package com.example.crediscountgo;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

public class Friend {
    private int iconId;
    private String name;
    private String school;
    private String major;
    private String role;

    public Friend(int iconId, String name, String school, String major, String role){
        this.iconId = iconId;
        this.name = name;
        this.school = school;
        this.major = major;
        this.role = role;
    }

    public int getIcon() { return this.iconId; }

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.school + ", " + this.major + ", " + this.role;
    }

    public static List<Friend> generateSampleFriendList(){
        List<Friend> friends = new ArrayList<>();

        friends.add(new Friend(R.drawable.ic_add_black_24dp,"Ben", "HKU", "CS", "Software Developer"));
        friends.add(new Friend(R.drawable.ic_launcher_background,"Sunday", "HKU", "MAJOR", "ROLE"));
        friends.add(new Friend(R.drawable.ic_launcher_foreground,"Frankie", "HKU", "MAJOR", "ROLE"));
        friends.add(new Friend(R.drawable.common_google_signin_btn_icon_dark,"Elvis", "HKU", "MAJOR", "ROLE"));
        friends.add(new Friend(R.drawable.common_google_signin_btn_icon_dark_normal,"Olivia", "HKU", "MAJOR", "ROLE"));
        friends.add(new Friend(R.drawable.logo_icon,"Kelvin", "HKU", "MAJOR", "ROLE"));

        return friends;
    }
}
