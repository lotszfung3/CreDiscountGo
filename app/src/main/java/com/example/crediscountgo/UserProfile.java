package com.example.crediscountgo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.crediscountgo.adapter.CreditCardAdapter;
import com.example.crediscountgo.adapter.WalletAdapter;
import com.example.crediscountgo.model.Discount;
import com.example.crediscountgo.model.MovieDiscount;
import com.example.crediscountgo.model.ShopDiscount;

import java.util.ArrayList;
import java.util.List;

public class UserProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

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
}
