package com.example.crediscountgo;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,OnMapReadyCallback {
    private GoogleMap mMap;
    private Context mContext;
    private PopupWindow filterPop;

    private double markerCoor[] = new double[20];
    private ArrayList<Circle> circleArrayList;
    private FloatingActionButton floatingActionButton;
    private RelativeLayout maprlayout;

    private RadioGroup filterRadioGroupCC;
    private RadioGroup filterRadioGroupS;
    private CheckBox chbox_c1,
            chbox_c2,
            chbox_c3,
            chbox_s1,
            chbox_s2;

    private Boolean c1_val;
    private Boolean c2_val;
    private Boolean c3_val;
    private Boolean s1_val;
    private Boolean s2_val;


    private FloatingActionButton filterActionButton;
    LatLng KT = new LatLng(22.3088477, 114.217971);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setUpMap();


        c1_val = true;
        c2_val = true;
        c3_val = true;
        s1_val = true;
        s2_val = true;


        setUpfilterBtn();


    }

    private void setUpfilterBtn() {

        mContext = getApplicationContext();
        maprlayout = (RelativeLayout) findViewById(R.id.maprl);


        filterActionButton = (FloatingActionButton)findViewById(R.id.filterBtn);
        filterActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                View customView = inflater.inflate(R.layout.filterpopup,null);

                filterPop = new PopupWindow(
                        customView, RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT
                );

                chbox_c1 = (CheckBox) customView.findViewById(R.id.cbox_c1);
                chbox_c2 = (CheckBox) customView.findViewById(R.id.cbox_c2);
                chbox_c3 = (CheckBox) customView.findViewById(R.id.cbox_c3);
                filterRadioGroupCC = (RadioGroup) customView.findViewById(R.id.radioGCreditCard);

                chbox_s1 = (CheckBox) customView.findViewById(R.id.cbox_s1);
                chbox_s2 = (CheckBox) customView.findViewById(R.id.cbox_s2);

                chbox_c1.setChecked(c1_val);
                chbox_c2.setChecked(c2_val);
                chbox_c3.setChecked(c3_val);
                chbox_s1.setChecked(s1_val);
                chbox_s2.setChecked(s2_val);

                filterRadioGroupS = (RadioGroup) customView.findViewById(R.id.radioGShops);



                if(Build.VERSION.SDK_INT>=21){
                    filterPop.setElevation(5.0f);
                }

                Button closeButton = (Button) customView.findViewById(R.id.filterOK);

                // Set a click listener for the popup window close button
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Dismiss the popup window

                        c1_val = chbox_c1.isChecked();
                        c2_val = chbox_c2.isChecked();
                        c3_val = chbox_c3.isChecked();
                        s1_val = chbox_s1.isChecked();
                        s2_val = chbox_s2.isChecked();

                        filterPop.dismiss();
                    }
                });


                filterPop.showAtLocation(maprlayout, Gravity.CENTER,0,0);


            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void setUpMap(){
        Random rand= new Random();
        for (int i=0;i < 10 ;i++)
        {

            markerCoor[2*i]= KT.latitude + rand.nextGaussian()/1000;
            markerCoor[2*i+1]= KT.longitude + rand.nextGaussian()/1000;
        }
        circleArrayList = new ArrayList<>(10);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.style_json));

            if (!success) {
                Log.e("fra", "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e("fra", "Can't find style. Error: ", e);
        }
        // Add a marker in Sydney and move the camera
        LatLng HK = new LatLng(22.2829369,114.1828038);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(HK,10));

        // Add circle
        for(int i=0;i<markerCoor.length/2;i++) {

            CircleOptions circleOptions = new CircleOptions().center(new LatLng(markerCoor[2*i],markerCoor[2*i+1])).radius(30).strokeWidth(3).fillColor(Color.argb(128, 255, 0, 0));
            circleArrayList.add(mMap.addCircle(circleOptions));
        }
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                initCameraPosition();
            }
        }, 1500);

    }
    private void initCameraPosition()
    {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(KT)      // Sets the center of the map to Mountain View
                .zoom(16.5f)                   // Sets the zoom
                .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition),2000,null);
    }
}
