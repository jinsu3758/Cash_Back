package com.example.jinsu.cash.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.example.jinsu.cash.R
import com.example.jinsu.cash.common.Constant
import kotlinx.android.synthetic.main.activity_my_page.*
import kotlinx.android.synthetic.main.content_my_page.view.*
import kotlinx.android.synthetic.main.navi_header.view.*

class MyPageActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    lateinit var glide : RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)
        glide = Glide.with(this)
        init()
    }

    fun init()
    {

        glide.load(R.drawable.profile).into(mypage_content.mypage_im_profile)
//        glide.load(R.drawable.tro1).apply(options).into(mypage_im_award)
        glide.load(R.drawable.account).into(mypage_content.im_my)
        glide.load(R.drawable.gift).into(mypage_content.im_point)
        glide.load(R.drawable.setting).into(mypage_content.im_setting)
        glide.load(R.drawable.logout).into(mypage_content.im_logout)
        mypage_content.mypage_toolbar.setNavigationIcon(getDrawable(R.drawable.menu))
        mypage_content.mypage_toolbar.title = getString(R.string.mypage)
        mypage_content.mypage_toolbar.setTitleTextColor(resources.getColor(R.color.white))
        setSupportActionBar(mypage_content.mypage_toolbar)
        val toggle = ActionBarDrawerToggle(this,mypage_layout,mypage_content.mypage_toolbar,
                0,0)
        mypage_layout.addDrawerListener(toggle)
        toggle.syncState()
        mypage_navi.setNavigationItemSelectedListener(this)
        Glide.with(this).load(R.drawable.my)
                .apply(RequestOptions().circleCrop())
                .into(mypage_navi.getHeaderView(0).navi_im_profile)
        Glide.with(this).load(R.drawable.my)
                .apply(RequestOptions().circleCrop())
                .into(mypage_content.mypage_im_profile)
        if(Constant.prefs.user_data != null)
        {
            val user = Constant.prefs.user_data
            mypage_navi.getHeaderView(0).navi_txt_id.setText(user!!.id.toString())
            mypage_content.mypage_txt_id.setText(user!!.id.toString())
            mypage_content.mypage_txt_nick.setText(user!!.nickname.toString())

        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.menu_nav_home ->
            {
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            }
            R.id.menu_nav_mypage ->
            {

            }
            R.id.menu_nav_chart ->
            {
                startActivity(Intent(this,ChartActivity::class.java))
            }
            R.id.menu_nav_shopping ->
            {
                startActivity(Intent(this,ShopActivity::class.java))
            }
        }
        mypage_layout.closeDrawer(GravityCompat.START)
        return true
    }
}