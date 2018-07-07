package com.example.crediscountgo;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.crediscountgo.adapter.CreditCardAdapter;
import com.example.crediscountgo.adapter.WalletAdapter;
import com.example.crediscountgo.model.Discount;
import com.example.crediscountgo.model.MovieDiscount;
import com.example.crediscountgo.model.ShopDiscount;

import java.util.ArrayList;
import java.util.List;

public class UserProfile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_base);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout1);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        List<Integer> creditCardIdList = new ArrayList<>();
        creditCardIdList.add(new Integer(R.drawable.hsbc_credit_card));
        creditCardIdList.add(new Integer(R.drawable.dbs_credit_card));
        creditCardIdList.add(new Integer(R.drawable.american_express_credit_card));

        List<Discount> discountsList = new ArrayList<>();
        MovieDiscount mDiscount = new MovieDiscount();
        mDiscount.setImgId(R.drawable.cinema2);
        mDiscount.setQrCodeId(R.drawable.qrcode1);

        ShopDiscount sDiscount = new ShopDiscount();
        sDiscount.setImgId(R.drawable.zara);
        sDiscount.setQrCodeId(R.drawable.qrcode3);

        ShopDiscount sDiscount1 = new ShopDiscount();
        sDiscount1.setImgId(R.drawable.sasa);
        sDiscount1.setQrCodeId(R.drawable.qrcode2);

        discountsList.add(mDiscount);
        discountsList.add(sDiscount);
        discountsList.add(sDiscount1);


        RecyclerView creditCardRecyclerView = (RecyclerView) findViewById(R.id.credit_card_recycler_view);
        CreditCardAdapter creditCardAdapter = new CreditCardAdapter(creditCardIdList);
        creditCardRecyclerView.setAdapter(creditCardAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        creditCardRecyclerView.setLayoutManager(layoutManager);

        RecyclerView walletRecyclerView = (RecyclerView) findViewById(R.id.wallet_recycler_view);
        WalletAdapter wAdapter = new WalletAdapter(discountsList);
        walletRecyclerView.setAdapter(wAdapter);

        LinearLayoutManager wLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        walletRecyclerView.setLayoutManager(wLayoutManager);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout1);
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

        if (id == R.id.map) {
            Intent startProdile = new Intent(this, MainActivity.class);
            startActivity(startProdile);


        } else if (id == R.id.profile) {
            Intent startProdile = new Intent(this, UserProfile.class);
            startActivity(startProdile);


        } else if (id == R.id.friends) {
            Intent startProdile = new Intent(this, FriendListActivity.class);
            startActivity(startProdile);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout1);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
