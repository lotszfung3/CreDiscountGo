package com.example.crediscountgo;

import android.app.Activity;
import android.net.Uri;
import android.view.Gravity;
import android.widget.Toast;

import com.google.ar.sceneform.rendering.ModelRenderable;

import java.util.Calendar;
import java.util.Date;

public class MovieDiscount extends Discount{

    public MovieDiscount(Activity activity){

        this.setTitle("50% off Movie Ticket Discount");
        this.setQrCodeContent("12345678910249710284710284e7910284710284");
        this.setStartDate("7/7/18");
        this.setStartDate("7/8/18");
        this.setMessage("Congratualations! You have won a 50% off discount in all cinemas in Hong Kong!");

    }
}
