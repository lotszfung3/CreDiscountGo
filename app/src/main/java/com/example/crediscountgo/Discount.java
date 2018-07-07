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
    String issuer;
    String cardScheme;


    public Discount(String mID, String sn, String sd, String ld, String c, String i){
        markerID = mID;
        shopName = sn;
        shortDis = sd;
        longDis = ld;
        card = c;
        issuer = i;

    }
    public String getIssuer(){
        return issuer;
    }
    public String getCardScheme(){
        return cardScheme;
    }
    public void setCardScheme(String scs){
        cardScheme = scs;
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
