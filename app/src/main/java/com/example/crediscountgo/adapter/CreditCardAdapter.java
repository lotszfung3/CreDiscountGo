package com.example.crediscountgo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.crediscountgo.R;

import java.util.List;

public class CreditCardAdapter extends RecyclerView.Adapter<CreditCardAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView creditImageView;

        public ViewHolder(View itemView){
            super(itemView);

            creditImageView = (ImageView) itemView.findViewById(R.id.credit_card_image);

        }
    }

    private List<Integer> imageIdList;

    public CreditCardAdapter(List<Integer> imageIdList){ this.imageIdList = imageIdList;}

    @Override
    public int getItemCount() {
        return imageIdList.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View contactView = LayoutInflater.from(context).inflate(R.layout.item_credit_card, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Integer imageId = imageIdList.get(position);
        ImageView creditImageView = holder.creditImageView;

        creditImageView.setBackgroundResource(imageId.intValue());
    }
}
