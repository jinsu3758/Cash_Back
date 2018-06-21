package com.example.jinsu.cash.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jinsu.cash.R;
import com.example.jinsu.cash.model.Shop;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {

    private ArrayList<Shop> list;
    private Context context;

    public ShopAdapter(Context context, ArrayList<Shop> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shop, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.shopTxtBrand.setText(list.get(position).getBrand());
        holder.shopTxtName.setText(list.get(position).getName());
        holder.shopTxtPrice.setText(list.get(position).getPoint());
        Glide.with(context).load(R.drawable.dot_yellow).into(holder.shopImDot);
        Glide.with(context).load(list.get(position).getIm_url()).into(holder.shopImItem);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.shop_im_item)
        ImageView shopImItem;
        @BindView(R.id.shop_im_dot)
        ImageView shopImDot;
        @BindView(R.id.shop_txt_brand)
        TextView shopTxtBrand;
        @BindView(R.id.shop_txt_name)
        TextView shopTxtName;
        @BindView(R.id.shop_txt_price)
        TextView shopTxtPrice;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

    }
}
