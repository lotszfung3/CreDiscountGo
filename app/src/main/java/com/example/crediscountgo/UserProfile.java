package com.example.crediscountgo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        List<Integer> creditCardIdList = new ArrayList<>();
        creditCardIdList.add(new Integer(R.drawable.full_logo));
        creditCardIdList.add(new Integer(R.drawable.full_logo));
        creditCardIdList.add(new Integer(R.drawable.full_logo));
        creditCardIdList.add(new Integer(R.drawable.full_logo));
        creditCardIdList.add(new Integer(R.drawable.full_logo));


        RecyclerView creditCardRecyclerView = (RecyclerView) findViewById(R.id.credit_card_recycler_view);
        CreditCardAdapter creditCardAdapter = new CreditCardAdapter(creditCardIdList);
        creditCardRecyclerView.setAdapter(creditCardAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        creditCardRecyclerView.setLayoutManager(layoutManager);
    }
}
