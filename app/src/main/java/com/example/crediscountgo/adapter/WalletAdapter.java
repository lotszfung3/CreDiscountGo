package com.example.crediscountgo.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.crediscountgo.R;
import com.example.crediscountgo.model.Discount;

import java.util.List;

public class WalletAdapter extends RecyclerView.Adapter<WalletAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView walletImgView;
        public TextView walletTextView;


        public ViewHolder(View itemView){
            super(itemView);

            walletImgView = (ImageView) itemView.findViewById(R.id.wallet_image);
            walletTextView = itemView.findViewById(R.id.wallet_text);

        }
    }

    private List<Discount> discountList;

    public WalletAdapter(List<Discount> discountList){ this.discountList = discountList;}

    @Override
    public int getItemCount() {
        return discountList.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View contactView = LayoutInflater.from(context).inflate(R.layout.item_wallet, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Integer imageId = discountList.get(position).getImgId();
        ImageView walletImgView = holder.walletImgView;

        walletImgView.setBackgroundResource(imageId);

        String text = discountList.get(position).getTitle();
        TextView walletTextView = holder.walletTextView;

        walletTextView.setText(text);


    }
}
