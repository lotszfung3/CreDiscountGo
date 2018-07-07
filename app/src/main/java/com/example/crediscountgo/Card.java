package com.example.crediscountgo;

public class Card {
    String initials;
    String iconURL;

    public Card(String i, String u){
        initials = i;
        iconURL = u;
    }

    public String getInitials(){
        return initials;
    }

    public String getIconURL(){
        return iconURL;
    }

}
