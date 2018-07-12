package com.example.jinsu.cash.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.jinsu.cash.R;
import com.example.jinsu.cash.adapter.ShopAdapter;
import com.example.jinsu.cash.model.Shop;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopActivity extends AppCompatActivity {

    @BindView(R.id.shop_im_coffee)
    ImageView shopImCoffee;
    @BindView(R.id.shop_layout_coffee)
    LinearLayout shopLayoutCoffee;
    @BindView(R.id.shop_im_bakery)
    ImageView shopImBakery;
    @BindView(R.id.shop_layout_bakery)
    LinearLayout shopLayoutBakery;
    @BindView(R.id.shop_im_desert)
    ImageView shopImDesert;
    @BindView(R.id.shop_layout_desert)
    LinearLayout shopLayoutDesert;
    @BindView(R.id.shop_im_more)
    ImageView shopImMore;
    @BindView(R.id.shop_layout_more)
    LinearLayout shopLayoutMore;
    @BindView(R.id.shop_recycler)
    RecyclerView shopRecycler;
    private ArrayList<Shop> shop_list;
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        ButterKnife.bind(this);
        initRecyclerVIew();
        initListener();

    }

    private void initRecyclerVIew() {
        shop_list = new ArrayList<>();
        mAdapter = new ShopAdapter(this, shop_list);
        shopRecycler.setHasFixedSize(true);
        shopRecycler.setLayoutManager(new GridLayoutManager(this,2));
        shopRecycler.setAdapter(mAdapter);
        setCoffe();

    }

    private void setCoffe() {
        shop_list.clear();

            shop_list.add(new Shop("http://cfile29.uf.tistory.com/image/165587494F72CE8826BCF5",
                    "스타벅스", "아메리카노", "3000p"));
            shop_list.add(new Shop("http://fimg2.pann.com/new/download.jsp?FileID=26583713",
                    "스타벅스", "아메리카노", "3000p"));
        shop_list.add(new Shop("http://cfile6.uf.tistory.com/image/2774BE41543B4018101886",
                "스타벅스", "아메리카노", "3000p"));
        shop_list.add(new Shop("http://img.newspim.com/news/2017/07/11/1707110912451040.jpg",
                "스타벅스", "아메리카노", "3000p"));
        shop_list.add(new Shop("http://archivenew.vop.co.kr/images/ebcb408fc84e59269558238d19ab771b/2017-03/22040252_79c728765.jpg",
                "스타벅스", "아메리카노", "3000p"));
        shop_list.add(new Shop("http://cfile229.uf.daum.net/image/255C3D3B5277386E0C276E",
                "스타벅스", "아메리카노", "3000p"));shop_list.add(new Shop("http://fimg2.pann.com/new/download.jsp?FileID=26583713",
                "스타벅스", "아메리카노", "3000p"));
        shop_list.add(new Shop("http://img.newspim.com/news/2017/07/11/1707110912451040.jpg",
                "스타벅스", "아메리카노", "3000p"));
        shop_list.add(new Shop("http://cfile28.uf.tistory.com/image/26280E44533A474A0623C2",
                "스타벅스", "아메리카노", "3000p"));
        shop_list.add(new Shop("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRYeYvuWlYDJvSQvgxwwCD6OUGItO7QqFVlPTpAIBx_-_yoc-46",
                "스타벅스", "아메리카노", "3000p"));

        shop_list.add(new Shop("http://fimg2.pann.com/new/download.jsp?FileID=26583713",
                "스타벅스", "아메리카노", "3000p"));
        shop_list.add(new Shop("http://fimg2.pann.com/new/download.jsp?FileID=26583713",
                "스타벅스", "아메리카노", "3000p"));
        shop_list.add(new Shop("http://fimg2.pann.com/new/download.jsp?FileID=26583713",
                "스타벅스", "아메리카노", "3000p"));



        mAdapter.notifyDataSetChanged();

    }

    private void setBakery() {
        shop_list.clear();

            shop_list.add
                    (new Shop("https://www.paris.co.kr/data/product/aaa.JPG",
                    "파리바게트", "고로케", "1500p"));
        shop_list.add
                (new Shop("https://www.paris.co.kr/data/product/441.png",
                        "파리바게트", "고로케", "1500p"));
        shop_list.add
                (new Shop("https://www.paris.co.kr/data/product/Mocha.JPG",
                        "파리바게트", "고로케", "1500p"));
        shop_list.add
                (new Shop("https://www.paris.co.kr/data/product/mi.PNG",
                        "파리바게트", "고로케", "1500p"));
        shop_list.add
                (new Shop("https://www.paris.co.kr/data/product/gansic_044.jpg",
                        "파리바게트", "고로케", "1500p"));
        shop_list.add
                (new Shop("https://www.paris.co.kr/data/product/1(53).jpg",
                        "파리바게트", "고로케", "1500p"));
        shop_list.add
                (new Shop("https://www.paris.co.kr/data/product/[1]eum38663@550.jpg",
                        "파리바게트", "고로케", "1500p"));
        shop_list.add
                (new Shop("https://www.paris.co.kr/data/product/bagguette.jpg",
                        "파리바게트", "고로케", "1500p"));
        shop_list.add
                (new Shop("https://www.paris.co.kr/data/product/eum26774.jpg",
                        "파리바게트", "고로케", "1500p"));
        shop_list.add
                (new Shop("https://www.paris.co.kr/data/product/[1]%ED%86%A1%ED%86%A1%EC%94%B9%ED%9E%88%EB%8A%94%EC%BD%98%EB%B8%8C%EB%A0%88%EB%93%9C.jpg",
                        "파리바게트", "고로케", "1500p"));
        shop_list.add
                (new Shop("https://www.paris.co.kr/data/product/01(6).jpg",
                        "파리바게트", "고로케", "1500p"));
        shop_list.add
                (new Shop("https://www.paris.co.kr/data/product/[2]b.jpg",
                        "파리바게트", "고로케", "1500p"));
        shop_list.add
                (new Shop("https://www.paris.co.kr/data/product/aaa.JPG",
                        "파리바게트", "고로케", "1500p"));


        mAdapter.notifyDataSetChanged();

    }

    private void setDesert() {
        shop_list.clear();

            shop_list.add
                    (new Shop("http://www.twosome.co.kr//Twosome_file/PRODUCT/1419_big_img",
                            "투썸플레이스", "레드벨벳 케이크", "7500p"));
        shop_list.add
                (new Shop("http://gdimg.gmarket.co.kr/1296869304/still/600?ver=1522654185",
                        "투썸플레이스", "레드벨벳 케이크", "7500p"));
        shop_list.add
                (new Shop("http://www.twosome.co.kr/Twosome_file/PRODUCT/781_big_img",
                        "투썸플레이스", "레드벨벳 케이크", "7500p"));
        shop_list.add
                (new Shop("http://www.twosome.co.kr//Twosome_file/PRODUCT/1505_big_img",
                        "투썸플레이스", "레드벨벳 케이크", "7500p"));
        shop_list.add
                (new Shop("http://cakeloveyou.com/web/product/medium/201803/274_shop1_162534.jpg",
                        "투썸플레이스", "레드벨벳 케이크", "7500p"));
        shop_list.add
                (new Shop("https://www.twosome.co.kr:7009/Twosome_file/PRODUCT/2083_small_img",
                        "투썸플레이스", "레드벨벳 케이크", "7500p"));
        shop_list.add
                (new Shop("https://www.twosome.co.kr:7009/Twosome_file/PRODUCT/2082_small_img",
                        "투썸플레이스", "레드벨벳 케이크", "7500p"));
        shop_list.add
                (new Shop("http://image.auction.co.kr/itemimage/11/55/e5/1155e5b2e1.jpg",
                        "투썸플레이스", "레드벨벳 케이크", "7500p"));
        shop_list.add
                (new Shop("http://image.auction.co.kr/itemimage/11/55/e4/1155e4de71.jpg",
                        "투썸플레이스", "레드벨벳 케이크", "7500p"));
        shop_list.add
                (new Shop("http://www.knnews.co.kr/edb/nimages/2014/06/20140627.01010112000002.03L.jpg",
                        "투썸플레이스", "레드벨벳 케이크", "7500p"));
        shop_list.add
                (new Shop("http://www.twosome.co.kr//Twosome_file/PRODUCT/1885_big_img",
                        "투썸플레이스", "레드벨벳 케이크", "7500p"));
        shop_list.add
                (new Shop("https://img.siksinhot.com/place/1426011161342460.jpg?w=307&h=300&c=Y",
                        "투썸플레이스", "레드벨벳 케이크", "7500p"));
        shop_list.add
                (new Shop("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTU66MsPzuVKhwcCQlhEHC5KjEQliToNag78csYb895Uc6oiTRTfQ",
                        "투썸플레이스", "레드벨벳 케이크", "7500p"));

        mAdapter.notifyDataSetChanged();

    }


    private void initListener() {
        Glide.with(this).load(R.drawable.coffe).into(shopImCoffee);
        Glide.with(this).load(R.drawable.bakery).into(shopImBakery);
        Glide.with(this).load(R.drawable.desert).into(shopImDesert);
        Glide.with(this).load(R.drawable.burger).into(shopImMore);

        shopLayoutCoffee.setOnClickListener(v ->
        {
            setCoffe();
        });

        shopLayoutBakery.setOnClickListener(v -> {
                setBakery();
        });

        shopLayoutDesert.setOnClickListener(v ->
        {
            setDesert();
        });

        shopLayoutMore.setOnClickListener(v ->
        {

        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
