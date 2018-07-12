package com.example.jinsu.cash.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.jinsu.cash.R
import com.example.jinsu.cash.common.Constant
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlinx.android.synthetic.main.activity_chart.*
import kotlinx.android.synthetic.main.content_chart.*
import kotlinx.android.synthetic.main.content_chart.view.*
import kotlinx.android.synthetic.main.navi_header.view.*


class ChartActivity :  AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var chart_entries : ArrayList<Entry>
    lateinit var chart_entries2 : ArrayList<Entry>
    lateinit var chart_entries3 : ArrayList<Entry>
    lateinit var chart_entries4 : ArrayList<Entry>
    lateinit var chart_entries5 : ArrayList<Entry>


    lateinit var chart_dataset : LineDataSet
    lateinit var chart_dataset2 : LineDataSet
    lateinit var chart_dataset3 : LineDataSet
    lateinit var chart_dataset4 : LineDataSet
    lateinit var chart_dataset5 : LineDataSet


    lateinit var line_data_list : ArrayList<ILineDataSet>


    lateinit var chart_data : LineData

    lateinit var day_pie_entries : ArrayList<PieEntry>
    lateinit var day_pie_dataset : PieDataSet
    lateinit var day_pie_data : PieData
    lateinit var pie_colors : ArrayList<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart)

        initActivity()
        initListener()
        initChart()
    }

    fun initActivity()
    {
        chart_toolbar.navigationIcon = getDrawable(R.drawable.menu)
//        chart_toolbar.setNavigationIcon(getDrawable(R.drawable.menu))
        content_chart.chart_toolbar.title = getString(R.string.statics)
        content_chart.chart_toolbar.setTitleTextColor(resources.getColor(R.color.white))
        setSupportActionBar(content_chart.chart_toolbar)
        val toggle = ActionBarDrawerToggle(this,chart_layout,
                chart_layout.chart_toolbar,0,0)
        chart_layout.addDrawerListener(toggle)
        toggle.syncState()
        chart_navi.setNavigationItemSelectedListener(this)
        Glide.with(this).load(R.drawable.my)
                .apply(RequestOptions().circleCrop())
                .into(chart_navi.getHeaderView(0).navi_im_profile)
        if(Constant.prefs.user_data != null)
        {
            val user = Constant.prefs.user_data
            Log.d("main_at",user!!.nickname)
            chart_navi.getHeaderView(0).navi_txt_id.setText(user!!.id.toString())
        }
    }

    fun initListener()
    {
        chart_btn_week.setOnClickListener()
        {
            chart_btn_day.background = getDrawable(R.drawable.border_btn_left_round)
            chart_btn_month.background = getDrawable(R.drawable.border_btn_right_round)
            chart_btn_week.background = getDrawable(R.drawable.border_btn_center_pressed)
            chart.visibility = View.VISIBLE
            chart.animateY(3000)
            chart.invalidate()
            chart_day_pie.visibility = View.INVISIBLE
        }

        chart_btn_day.setOnClickListener()
        {
            chart_btn_day.background = getDrawable(R.drawable.border_btn_left_round_pressed)
            chart_btn_month.background = getDrawable(R.drawable.border_btn_right_round)
            chart_btn_week.background = getDrawable(R.drawable.border_btn_center)
            chart.visibility = View.INVISIBLE
            chart_day_pie.visibility = View.VISIBLE
            chart_day_pie.animateY(3000)
            chart_day_pie.invalidate()
        }

        chart_btn_month.setOnClickListener()
        {
            chart_btn_day.background = getDrawable(R.drawable.border_btn_left_round)
            chart_btn_month.background = getDrawable(R.drawable.border_btn_right_round_pressed)
            chart_btn_week.background = getDrawable(R.drawable.border_btn_center)
            chart.visibility = View.VISIBLE
            chart.animateY(3000)
            chart.invalidate()
            chart_day_pie.visibility = View.INVISIBLE
        }

    }
    fun initChart()
    {

        chart_day_pie.setUsePercentValues(true)
        chart_day_pie.getDescription().setEnabled(false)
        chart_day_pie.setExtraOffsets(5f, 10f, 5f, 5f)

        chart_day_pie.setDragDecelerationFrictionCoef(0.95f)

        chart_day_pie.setDrawHoleEnabled(false)
        chart_day_pie.setHoleColor(Color.WHITE)
        chart_day_pie.setTransparentCircleRadius(61f)
        chart_day_pie.setDrawHoleEnabled(true)
        chart_day_pie.setHoleColor(Color.WHITE)

        chart_day_pie.setTransparentCircleColor(Color.WHITE)
        chart_day_pie.setTransparentCircleAlpha(110)

        chart_day_pie.setHoleRadius(58f)
        chart_day_pie.setTransparentCircleRadius(61f)

        chart_day_pie.setDrawCenterText(true)
        chart_day_pie.setCenterTextSize(15f)
        chart_day_pie.setCenterText("총 10시간")//여기 sum한거 넣어야지

        chart_day_pie.setRotationAngle(0f)
        // enable rotation of the chart by touch
        chart_day_pie.setRotationEnabled(true)
        chart_day_pie.setHighlightPerTapEnabled(true)

        day_pie_entries = ArrayList<PieEntry>()
        day_pie_entries.add(PieEntry(35f))
        day_pie_entries.add(PieEntry(17f))
        day_pie_entries.add(PieEntry(20f))
        day_pie_entries.add(PieEntry(26f))
        day_pie_entries.add(PieEntry(19f))

        pie_colors = ArrayList<Int>()
        pie_colors.add(Color.rgb(25,197,154))
        pie_colors.add(Color.rgb(38,168,236))
        pie_colors.add(Color.rgb(112,94,176))
        pie_colors.add(Color.rgb(163,96,254))
        pie_colors.add(Color.rgb(181,217,233))

        day_pie_dataset = PieDataSet(day_pie_entries,"")
        day_pie_dataset.sliceSpace = 2f
        day_pie_dataset.selectionShift = 5f
        day_pie_dataset.setColors(pie_colors)

        day_pie_data = PieData(day_pie_dataset)
//        day_pie_data.setValueTextSize(0f)
        day_pie_data.setValueTextSize(10f)
        day_pie_data.setValueTextColor(Color.BLACK)

        val legend = chart_day_pie.legend
        legend.isEnabled = false

        chart_day_pie.data = day_pie_data
        chart_day_pie.animateY(2000)



        chart_entries = ArrayList<Entry>()
        chart_entries.add(Entry(1f,20f))
        chart_entries.add(Entry(2f,27f))
        chart_entries.add(Entry(3f,20f))
        chart_entries.add(Entry(4f,20f))
        chart_entries.add(Entry(5f,20f))
        chart_entries.add(Entry(6f,20f))
        chart_entries.add(Entry(7f,20f))

        chart_entries2 = ArrayList<Entry>()
        chart_entries2.add(Entry(1f,9f))
        chart_entries2.add(Entry(2f,12f))
        chart_entries2.add(Entry(3f,14f))
        chart_entries2.add(Entry(4f,22f))
        chart_entries2.add(Entry(5f,17f))
        chart_entries2.add(Entry(6f,10f))
        chart_entries2.add(Entry(7f,7f))

        chart_entries3 = ArrayList<Entry>()
        chart_entries3.add(Entry(1f,22f))
        chart_entries3.add(Entry(2f,15f))
        chart_entries3.add(Entry(3f,17f))
        chart_entries3.add(Entry(4f,10f))
        chart_entries3.add(Entry(5f,7f))
        chart_entries3.add(Entry(6f,27f))
        chart_entries3.add(Entry(7f,18f))

        chart_entries4 = ArrayList<Entry>()
        chart_entries4.add(Entry(1f,18f))
        chart_entries4.add(Entry(2f,7f))
        chart_entries4.add(Entry(3f,27f))
        chart_entries4.add(Entry(4f,9f))
        chart_entries4.add(Entry(5f,14f))
        chart_entries4.add(Entry(6f,23f))
        chart_entries4.add(Entry(7f,14f))

        chart_entries5 = ArrayList<Entry>()
        chart_entries5.add(Entry(1f,8f))
        chart_entries5.add(Entry(2f,11f))
        chart_entries5.add(Entry(3f,12f))
        chart_entries5.add(Entry(4f,10f))
        chart_entries5.add(Entry(5f,9f))
        chart_entries5.add(Entry(6f,13f))
        chart_entries5.add(Entry(7f,11f))

        chart_dataset = LineDataSet(chart_entries,"")
        chart_dataset.color = Color.rgb(25,197,154)
        chart_dataset.setCircleColor(Color.rgb(25,197,154))
        chart_dataset.lineWidth = 1f
        chart_dataset.circleRadius = 5f

        chart_dataset2 = LineDataSet(chart_entries2,"")
        chart_dataset2.color = Color.rgb(38,168,236)
        chart_dataset2.setCircleColor(Color.rgb(38,168,236))
        chart_dataset2.lineWidth = 1f
        chart_dataset2.circleRadius = 5f

        chart_dataset3 = LineDataSet(chart_entries3,"")
        chart_dataset3.color = Color.rgb(112,94,176)
        chart_dataset3.setCircleColor(Color.rgb(112,94,176))
        chart_dataset3.lineWidth = 1f
        chart_dataset3.circleRadius = 5f

        chart_dataset4 = LineDataSet(chart_entries4,"")
        chart_dataset4.color = Color.rgb(163,96,254)
        chart_dataset4.setCircleColor(Color.rgb(163,96,254))
        chart_dataset4.lineWidth = 1f
        chart_dataset4.circleRadius = 5f

        chart_dataset5 = LineDataSet(chart_entries5,"")
        chart_dataset5.color = Color.rgb(181,217,233)
        chart_dataset5.setCircleColor(Color.rgb(181,217,233))
        chart_dataset5.lineWidth = 1f
        chart_dataset5.circleRadius = 5f


        line_data_list = ArrayList<ILineDataSet>()
        line_data_list.add(chart_dataset)
        line_data_list.add(chart_dataset2)
        line_data_list.add(chart_dataset3)
        line_data_list.add(chart_dataset4)
        line_data_list.add(chart_dataset5)

        val quarters = arrayOf("월", "화", "수", "목","금","토","일")

        val formatter = object : IAxisValueFormatter {

            // we don't draw numbers, so no decimal digits needed
            val decimalDigits: Int
                get() = 0

            override fun getFormattedValue(value: Float, axis: AxisBase): String {
                return quarters[value.toInt()-1]
            }
        }

        val xAxis = chart.getXAxis()
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM)
        xAxis.granularity = 1f
        xAxis.setValueFormatter(formatter)
        xAxis.setDrawLabels(true)
        xAxis.setDrawGridLines(false)
        xAxis.setTextColor(Color.BLACK)


        val yLAxis = chart.axisLeft
        yLAxis.setTextColor(Color.BLACK)
        yLAxis.granularity =5f
//        yLAxis.setLabelCount(5,true)

//        yLAxis.setDrawGridLines(false)

        val yRAxis = chart.getAxisRight()
        yRAxis.setDrawLabels(false)
        yRAxis.setDrawAxisLine(false)
        yRAxis.setDrawGridLines(false)

        val des = Description()
        des.text = ""

        chart_data = LineData(line_data_list)

        chart.isDoubleTapToZoomEnabled = false
        chart.setDrawGridBackground(false)
        chart.data = chart_data
        chart.description = des
        chart.legend.isEnabled = false
//        chart.setDraw Borders(false)
        chart.setBorderColor(R.color.colorPrimary)

        chart.setBackgroundColor(resources.getColor(R.color.white))
        chart.animateY(5000)
        chart.invalidate()

    }

    fun initChart2()
    {

    }
    override fun onBackPressed() {
        if (chart_layout.isDrawerOpen(GravityCompat.START)) {
            chart_layout.closeDrawer(GravityCompat.START)
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

            }
            R.id.menu_nav_shopping ->
            {
                startActivity(Intent(this,ShopActivity::class.java))
            }
        }
        chart_layout.closeDrawer(GravityCompat.START)
        return true
    }
}