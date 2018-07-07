package com.example.crediscountgo.model;

import com.example.crediscountgo.R;

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

        friends.add(new Friend(R.drawable.ben_choi,"Ben Choi", "HKU", "CS", "Software Developer"));
        friends.add(new Friend(R.drawable.sunday_wong,"Sunday Wong", "HKU", "CS", "Software Develope"));
        friends.add(new Friend(R.drawable.ic_launcher_foreground,"Frankie Lo", "HKU", "CS", "Software Develope"));
        friends.add(new Friend(R.drawable.elvis_ngai,"Elvis Ngai", "HKU", "MAJOR", "ROLE"));
        friends.add(new Friend(R.drawable.olivia_lai,"Olivia Lai", "HKU", "CS", "Software Develope"));
        friends.add(new Friend(R.drawable.kelvin_wong,"Kelvin Wong", "HKU", "MAJOR", "ROLE"));

        return friends;
    }
}
