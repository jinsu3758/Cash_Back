package com.example.jinsu.cash.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.jinsu.cash.R
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet




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
        
        /*chart_entries = ArrayList<Entry>()
        chart_entries.add(Entry(1f,0f))
        chart_entries.add(Entry(2f,3f))
        chart_entries.add(Entry(3f,5f))
        chart_entries.add(Entry(4f,2f))



        chart_dataset = LineDataSet(chart_entries,"바른 자세")
        chart_dataset.setColor(Color.RED)
        chart_dataset.setCircleColor(Color.RED)
        chart_dataset.lineWidth = 1f
        chart_dataset.circleRadius = 6f


        val quarters = arrayOf("월", "화", "수", "목")

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


        val yLAxis = chart.getAxisLeft()
        yLAxis.setTextColor(Color.BLACK)
        yLAxis.granularity = 5f
        yLAxis.setDrawGridLines(false)

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
        chart.invalidate()*/

    }
}