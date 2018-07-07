package com.example.crediscountgo;

import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;

import java.util.Locale;

public class Discount {
    String markerID;
    String shopName;
    String shopAddrLine;
    String shortDis;
    String longDis;
    String card;


    public Discount(String mID, String sn, String sd, String ld, String c){
        markerID = mID;
        shopName = sn;
        shortDis = sd;
        longDis = ld;
        card = c;

    }
    public String getMarkerID(){
        return markerID;
    }

    public void setShopName(String sn){
        shopName = sn;
    }
    public String getShopName(){
        return shopName;
    }

    public void setShopAddrLine(String al){
        shopAddrLine = al;
    }
    public String getShopAddrLine(){
        return shopAddrLine;
    }
    public void setShortDis(String sd){
        shortDis = sd;
    }
    public String getShortDis(){
        return shortDis;
    }
    public void setLongDis(String sd){
        longDis = sd;
    }
    public String getLongDis(){
        return longDis;
    }
    public void setCard(String sd){
        card = sd;
    }
    public String getCard(){
        return card;
    }
}
