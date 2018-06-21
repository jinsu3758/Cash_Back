package com.example.jinsu.cash.activity

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.jinsu.cash.R
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.activity_chart.*


class ChartActivity :  AppCompatActivity() {
    lateinit var chart_entries : ArrayList<Entry>
    lateinit var chart_dataset : LineDataSet
    lateinit var chart_data : LineData
    lateinit var labels : ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart)

        init()
    }

    fun init()
    {
        chart_entries = ArrayList<Entry>()
        chart_entries.add(Entry(4f,0f))
        chart_entries.add(Entry(5f,1f))
        chart_entries.add(Entry(6f,2f))



        chart_dataset = LineDataSet(chart_entries,"# of Calls")
        chart_dataset.setColor(Color.RED)
        chart_dataset.setCircleColor(Color.RED)
        chart_dataset.lineWidth = 2f
        chart_dataset.circleRadius = 6f


        val xAxis = chart.getXAxis()
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM)
        xAxis.setTextColor(Color.BLACK)
        xAxis.enableGridDashedLine(8f, 24f, 0f)

        val yLAxis = chart.getAxisLeft()
        yLAxis.setTextColor(Color.BLACK)

        val yRAxis = chart.getAxisRight()
        yRAxis.setDrawLabels(false)
        yRAxis.setDrawAxisLine(false)
        yRAxis.setDrawGridLines(false)

        labels = ArrayList<String>()
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");
        labels.add("July");
        labels.add("August");
        labels.add("September");
        labels.add("October");
        labels.add("November");
        labels.add("December");

        chart_data = LineData(chart_dataset)

        chart.isDoubleTapToZoomEnabled = false
        chart.setDrawGridBackground(false)
        chart.data = chart_data
//        chart.setDraw Borders(false)
        chart.setBorderColor(R.color.colorPrimary)

        chart.setBackgroundColor(resources.getColor(R.color.white))
        chart.animateY(5000)
        chart.invalidate()

    }
}