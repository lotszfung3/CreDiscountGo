package com.example.crediscountgo;

import android.app.Activity;
import android.net.Uri;
import android.view.Gravity;
import android.widget.Toast;

import com.google.ar.sceneform.rendering.ModelRenderable;

import java.util.Calendar;
import java.util.Date;

public class MovieDiscount extends Discount{

    public MovieDiscount(){

        this.setTitle("Movie Ticket Discount");
        this.setQrCodeContent("12345678910249710284710284e7910284710284");
        this.setStartDate("7/7/18");
        this.setStartDate("7/8/18");
        this.setMessage("50% off discount in ALL cinemas \n in Hong Kong!");

    }
}
