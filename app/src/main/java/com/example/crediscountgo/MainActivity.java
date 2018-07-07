package com.example.crediscountgo;

import android.content.Context;
import android.content.Intent;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.graphics.Canvas;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;

import android.graphics.drawable.Drawable;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.DrawableRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RoundCap;
import com.google.maps.android.PolyUtil;



import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener, GoogleMap.OnMapClickListener,GoogleMap.OnMarkerClickListener {
    private GoogleMap mMap;

    private Context mContext;
    private PopupWindow filterPop;
    private PopupWindow shopPop;

    private RelativeLayout maprlayout;

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

    private View customView;
    private View customViewShop;


    private FloatingActionButton filterActionButton;

    private Polyline polyline;
    private static final int COLOR_BLACK_ARGB = 0xffccaa70;
    private static final int POLYLINE_STROKE_WIDTH_PX = 15;
    private double markerCoor[] = {
            22.319960, 114.171100,
            22.317043339029887,114.1712237522006,
            22.31716895493705,114.17005430907011,
            22.319859279350407,114.16982866823673,

            22.318747053294828,114.1710225865245,
            22.317966070527866,114.16876550763845,
            22.318511332189384,114.17217459529638


    };



    private double treasureCoor[]={
            22.320154237980244,114.17336348444223,
            22.322470773620328,114.16858848184347
    };
    private ArrayList<Marker> markerArrayList;
    LatLng MK = new LatLng(22.318188, 114.170216);

    private ArrayList<Discount> discountMarkersArrayList;


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

        maprlayout = (RelativeLayout) findViewById(R.id.maprl);
        setUpfilterBtn();

        markerArrayList=new ArrayList<>(5);
        discountMarkersArrayList=new ArrayList<>(5);

        initDiscountMarkersArrayList();




    }

    private void initDiscountMarkersArrayList(){
        Discount tempDis0 = new Discount("m0", "m0 Shop Name", "10% off", "LongDis of m0", "AE");
        Discount tempDis1= new Discount("m1", "m1 Shop Name", "5% off", "LongDis of m1", "DBS");
        Discount tempDis2= new Discount("m2", "m2 Shop Name", "20% off", "LongDis of m2", "HSBC");
        Discount tempDis3= new Discount("m3", "m3 Shop Name",  "Half Price", "LongDis of m3", "AE");
        Discount tempDis4= new Discount("m4", "m4 Shop Name", "15% off", "LongDis of m4", "AE");
        Discount tempDis5= new Discount("m5", "m5 Shop Name", "2% off", "LongDis of m5", "AE");
        Discount tempDis6= new Discount("m6", "m6 Shop Name", "30% off", "LongDis of m6", "AE");
        discountMarkersArrayList.add(tempDis0);
        discountMarkersArrayList.add(tempDis1);
        discountMarkersArrayList.add(tempDis2);
        discountMarkersArrayList.add(tempDis3);
        discountMarkersArrayList.add(tempDis4);

        Geocoder gc = new Geocoder(MainActivity.this, Locale.ENGLISH);
        for (int i=0;i<discountMarkersArrayList.size();i++) {
            Discount tempD = discountMarkersArrayList.get(i);

            List<Address> listAddr = null;

            for (int j=0; j<markerArrayList.size(); j++){
                Marker tempM = markerArrayList.get(j);
                if (tempD.getMarkerID() == tempM.getId()){
                    LatLng ll = tempM.getPosition();

                    try {
                        listAddr = gc.getFromLocation(ll.latitude, ll.longitude, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                    String addrLine = "" + listAddr.get(0).getAddressLine(0);
                    tempD.setShopAddrLine(addrLine);
                    discountMarkersArrayList.set(i, tempD);
                }
            }


        }



    }

    private void setUpfilterBtn() {

        mContext = getApplicationContext();



        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
        customView = inflater.inflate(R.layout.filterpopup, null);

        filterPop = new PopupWindow(
                customView, RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        chbox_c1 = (CheckBox) customView.findViewById(R.id.cbox_c1);
        chbox_c2 = (CheckBox) customView.findViewById(R.id.cbox_c2);
        chbox_c3 = (CheckBox) customView.findViewById(R.id.cbox_c3);

        chbox_s1 = (CheckBox) customView.findViewById(R.id.cbox_s1);
        chbox_s2 = (CheckBox) customView.findViewById(R.id.cbox_s2);


        filterActionButton = (FloatingActionButton)findViewById(R.id.filterBtn);
        filterActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                chbox_c1.setChecked(c1_val);
                chbox_c2.setChecked(c2_val);
                chbox_c3.setChecked(c3_val);
                chbox_s1.setChecked(s1_val);
                chbox_s2.setChecked(s2_val);



                if (Build.VERSION.SDK_INT >= 21) {
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

                        filterMarkers((s1_val ? 1 : 0) +(s2_val ? 1 : 0)*2);
                        filterPop.dismiss();
                    }
                });


                filterPop.showAtLocation(maprlayout, Gravity.CENTER, 0, 0);

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

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.getUiSettings().setMapToolbarEnabled(false);
        mMap.setOnInfoWindowClickListener(this);
        mMap.setOnMapClickListener(this);
        mMap.setOnMarkerClickListener(this);
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
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                initCameraPosition();
                // Add markers
            }
        }, 1500);

    }
    private void initCameraPosition()
    {
        //initialize marker name

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(MK)      // Sets the center of the map to Mountain View
                .zoom(17f)                   // Sets the zoom
                .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 2000, new GoogleMap.CancelableCallback() {
            @Override
            public void onFinish() {

                for(int i=0;i<markerCoor.length/2;i++) {
                    if(i<3)

                        markerArrayList.add( mMap.addMarker(new MarkerOptions().position(new LatLng(markerCoor[2*i],markerCoor[2*i+1])).snippet("marker: "+i).title("asdasd").icon(vectorToBitmap(R.drawable.ic_local_dining_black_24dp))));
                    else
                        markerArrayList.add(mMap.addMarker(new MarkerOptions().position(new LatLng(markerCoor[2 * i], markerCoor[2 * i + 1])).snippet("marker: " + i).title("asdasd").icon(vectorToBitmap((R.drawable.ic_store_mall_directory_black_24dp)))));
                }
                Bitmap img = BitmapFactory.decodeResource(getResources(),R.drawable.treasure_2);
                BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(img);
                for (int i=0;i<treasureCoor.length/2;i++){
                    mMap.addMarker( new MarkerOptions().position(new LatLng(treasureCoor[2*i],treasureCoor[2*i+1])).snippet("marker: "+i).title("asdasd").icon(bitmapDescriptor));
                }

                mMap.addMarker(new MarkerOptions().position(new LatLng(22.316434177817605,114.16926439851522)).icon(vectorToBitmap(R.drawable.ic_tag_faces_black_24dp)));

                polyline= mMap.addPolyline(new PolylineOptions().addAll(PolyUtil.decode("utegCwtywTeI`Aa@FQsAOsA?[Kw@uBT}@JAMM@@Ne@Do@J")));
                // Use a round cap at the start of the line.
                polyline.setStartCap(new RoundCap());


                polyline.setEndCap(new RoundCap());
                polyline.setWidth(POLYLINE_STROKE_WIDTH_PX);
                polyline.setColor(COLOR_BLACK_ARGB);
                polyline.setJointType(JointType.ROUND);
                polyline.setWidth(15);
                polyline.setVisible(false);

            }

            @Override
            public void onCancel() {

            }
        });
    }
    @Override
    public void onInfoWindowClick(Marker marker) {
        /*
        Log.v("frankie","asdasdsa");
        Toast.makeText(this, "Info window clicked",
                Toast.LENGTH_SHORT).show();
*/


        /*
        Geocoder gc = new Geocoder(MainActivity.this, Locale.ENGLISH);
        List<Address> listAddr = null;
        LatLng ll = marker.getPosition();
        try {
            listAddr = gc.getFromLocation(ll.latitude, ll.longitude, 1);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        String addrLine = "" + listAddr.get(0).getAddressLine(0);
        Log.d("address line",addrLine);

        String shopName = markerName.get(marker.getId());
*/


        /*
        Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);
        */

    }

    @Override
    public void onMapClick(LatLng latLng) {
        Log.d("Frankie",latLng.latitude+","+latLng.longitude);
    }
    private void filterMarkers(int id)
    {
        polyline.setVisible(false);
        boolean mask1=(id==1 || id==3);
        boolean mask2=(id>1);

            for (int i=0;i<markerArrayList.size();i++) {
                if (i < 3)
                    markerArrayList.get(i).setVisible(mask1);
                else
                    markerArrayList.get(i).setVisible(mask2);
            }


    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        LayoutInflater inflater2 = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
        customViewShop = inflater2.inflate(R.layout.shopshortdetails, null);

        shopPop = new PopupWindow(
                customViewShop, RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        TextView shopName = (TextView) customViewShop.findViewById(R.id.ss_ShopName);
        TextView shopAddr = (TextView) customViewShop.findViewById(R.id.ss_AddrLine);
        TextView shopShortDis = (TextView) customViewShop.findViewById(R.id.ss_shortDis);
        TextView shopCard = (TextView) customViewShop.findViewById(R.id.ss_creditCard);
        Log.v("size",Integer.toString(discountMarkersArrayList.size()));
        Log.v("marker match","target marker: "+marker.getId());
        for (int i=0;i<discountMarkersArrayList.size();i++) {
            Discount tempD = discountMarkersArrayList.get(i);
            Log.v("marker match","markerID looop: "+tempD.getMarkerID());


            if (tempD.getMarkerID().equals(marker.getId())) {
                Log.v("marker match",tempD.getMarkerID());
                Log.v("marker match",tempD.getShopName());
                shopName.setText(tempD.getShopName());
                shopAddr.setText(tempD.getShopAddrLine());
                shopShortDis.setText(tempD.getShortDis());
                shopCard.setText(tempD.getCard());
            }

        }

        Button closeButton = (Button) customViewShop.findViewById(R.id.ss_close);

        // Set a click listener for the popup window close button
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Dismiss the popup window

                shopPop.dismiss();
            }
        });

        shopPop.showAtLocation(maprlayout, Gravity.CENTER, 0, 0);



        Log.v("Frankie",marker.getId());
        if(marker.getId().equals("m3")) {
            polyline.setPoints(PolyUtil.decode("utegCwtywTeI`Aa@FQsAOsA?[Kw@uBT}@JAMM@@Ne@Do@J"));
            polyline.setVisible(true);
        }
        else if (marker.getId().equals("m4")) {
            polyline.setPoints(PolyUtil.decode("utegCwtywTeI`Aa@FQsAOsA?[Kw@E?G}@SsBUD"));
            polyline.setVisible(true);
        }
        else if (marker.getId().equals("m5")) {
            polyline.setPoints(PolyUtil.decode("utegCwtywTwH~@"));
            polyline.setVisible(true);
        }
        else if (marker.getId().equals("m6")) {
            polyline.setPoints(PolyUtil.decode("utegCwtywTeI`Aa@FQsAOsA?[Kw@FAG}@SuBKy@KeBAOj@G"));
            polyline.setVisible(true);
        }
        else
            polyline.setVisible(false);
        return false;
    }
    private BitmapDescriptor vectorToBitmap(@DrawableRes int id) {
        Drawable vectorDrawable = ResourcesCompat.getDrawable(getResources(), id, null);
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),
                vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
}
