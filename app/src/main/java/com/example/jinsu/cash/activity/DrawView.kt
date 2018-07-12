package com.example.jinsu.cash.activity

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.example.jinsu.cash.R

class DrawView : View {
    private var angle1 = 0f
    private var angle2 = 0f
    lateinit private var paint: Paint
    lateinit private var rf: RectF
    lateinit private var rf2: RectF
    lateinit private var rf3: RectF
    lateinit private var rf4: RectF


    constructor(context: Context) : super(context) {

    }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    {
        paint = Paint()
        paint.color = Color.GRAY
        paint.textSize = 22f
        paint.isAntiAlias = true

        paint.style = Paint.Style.STROKE //원의 윤곽선만 그리는 페인트 스타일
        paint.alpha = 0x00
        paint.strokeWidth = 5f


        //부채꼴
        rf = RectF(204f, 156f, 897f, 849f)
        rf2 = RectF(184f, 136f, 917f, 869f)
        rf3 = RectF(174f, 126f, 927f, 879f)
        rf4 = RectF(154f, 106f, 947f, 899f)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        /*paint.color = resources.getColor(R.color.gray2)
        paint.strokeWidth = 15f
        canvas.drawArc(rf, 0f, 360f, false, paint)*/
        //sweepAngle : 몇도 그릴지, useCenter : true(부채꼴)
        paint.color = resources.getColor(R.color.colorPrimary)
        canvas.drawArc(rf, 270f, angle1, false, paint)

        canvas.drawArc(rf2, 270f, angle1, false, paint)

        paint.color = resources.getColor(R.color.red)

        canvas.drawArc(rf3, 270f, angle2, false, paint)
        canvas.drawArc(rf4, 270f, angle2, false, paint)


    }

    fun getAngle1(): Float {
        return angle1
    }

    fun setAngle1(angle1: Float) {
        this.angle1 = angle1
    }

    fun getAngle2(): Float {
        return angle2
    }

    fun setAngle2(angle2: Float) {
        this.angle2 = angle2
    }

}