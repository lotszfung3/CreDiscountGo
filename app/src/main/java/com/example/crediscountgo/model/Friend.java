package com.example.crediscountgo.model;

import com.example.crediscountgo.R;

import java.util.ArrayList;
import java.util.List;

public class Friend {
    private int iconId;
    private String name;
    private String school;
    private String major;

    public String getRole() {
        return role;
    }

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
        return this.school + ", " + this.major;
    }

    public static List<Friend> generateSampleFriendList(){
        List<Friend> friends = new ArrayList<>();

        friends.add(new Friend(R.drawable.elvis_ngai,"Elvis Ngai", "HKU", "BBA(IBGM)", "Business Management & Development"));
        friends.add(new Friend(R.drawable.kelvin_wong,"Kelvin Wong", "HKU", "BBA(IBGM)", "Finance & Marketing"));

        friends.add(new Friend(R.drawable.ben_choi,"Ben Choi", "HKU", "BEng(CS)", "Full Stack Developer"));
        friends.add(new Friend(R.drawable.frankie_lo1,"Frankie Lo", "HKU", "BEng(CS)", "Full Stack Developer"));
        friends.add(new Friend(R.drawable.olivia_lai,"Olivia Lai", "HKU", "BEng(CS)", "Front-End Developer"));
        friends.add(new Friend(R.drawable.sunday_wong,"Sunday Wong", "HKU", "BEng(CS)", "Product Manager"));


        return friends;
    }
}
