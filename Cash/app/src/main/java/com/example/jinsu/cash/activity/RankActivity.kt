package com.example.jinsu.cash.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.example.jinsu.cash.R
import com.example.jinsu.cash.adapter.RankAdapter
import com.example.jinsu.cash.common.Constant
import com.example.jinsu.cash.model.Rank
import kotlinx.android.synthetic.main.activity_rank.*
import kotlinx.android.synthetic.main.content_rank.*
import kotlinx.android.synthetic.main.content_rank.view.*
import kotlinx.android.synthetic.main.navi_header.view.*

class RankActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var adapter : RankAdapter
    lateinit var list : ArrayList<Rank>
    lateinit var requestManager: RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rank)
        initActivity()
        initRecyclerView()
    }

    private fun initActivity()
    {
        content_rank.rank_toolbar.navigationIcon = getDrawable(R.drawable.logo)
        content_rank.rank_toolbar.title = getString(R.string.rank)
        content_rank.rank_toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        setSupportActionBar(content_rank.rank_toolbar)
        val toggle = ActionBarDrawerToggle(this,rank_layout,content_rank.rank_toolbar
                ,0,0)
        rank_layout.addDrawerListener(toggle)
        toggle.syncState()
        rank_navi.setNavigationItemSelectedListener(this)
        requestManager = Glide.with(this)

        requestManager.load(R.drawable.my).apply(RequestOptions().circleCrop())
                .into(rank_navi.getHeaderView(0).navi_im_profile)
        if(Constant.prefs.user_data != null)
        {
            val user = Constant.prefs.user_data
            Log.d("main_at",user!!.nickname)
            rank_navi.getHeaderView(0).navi_txt_id.text = user!!.id.toString()
        }
    }

    private fun initRecyclerView()
    {
        list = ArrayList()
        adapter = RankAdapter(list,requestManager)
        rank_recycler.setHasFixedSize(true)
        rank_recycler.layoutManager = LinearLayoutManager(this)
        rank_recycler.adapter = adapter
    }


    private fun setList()
    {
        list.clear()
        list.add(Rank("1", "피카츄", "25900"))
        list.add(Rank("2", "꼬부기", "22340"))
        list.add(Rank("3", "파이리", "20540"))
        list.add(Rank("4", "야도란", "18590"))
        list.add(Rank("5", "꼬렛", "17560"))
        list.add(Rank("6", "라이츄", "17430"))
        list.add(Rank("7", "또도가스", "16900"))
        list.add(Rank("8", "닥트리오", "15630"))
        list.add(Rank("9", "깨비드릴조", "15270"))
        list.add(Rank("10", "이브이", "13240"))
        list.add(Rank("11", "망나뇽", "13100"))
        list.add(Rank("12", "프리저", "10200"))
        list.add(Rank("13", "피존투", "9500"))
        list.add(Rank("14", "구구", "9150"))
        list.add(Rank("15", "찌리리공", "7850"))
        list.add(Rank("16", "아보크", "6930"))


        adapter.notifyDataSetChanged()
    }


    override fun onBackPressed() {
        if (rank_layout.isDrawerOpen(GravityCompat.START)) {
            rank_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.menu_nav_home ->
            {
                startActivity(Intent(this,MainActivity::class.java))
            }
            R.id.menu_nav_mypage ->
            {
                startActivity(Intent(this,MyPageActivity::class.java))
            }
            R.id.menu_nav_chart ->
            {
                startActivity(Intent(this,ChartActivity::class.java))
            }
            R.id.menu_nav_shopping ->
            {
                startActivity(Intent(this,ShopActivity::class.java))
            }
            R.id.menu_nav_rank ->
            {

            }
        }
        rank_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onResume() {
        super.onResume()

        setList()
    }
}
