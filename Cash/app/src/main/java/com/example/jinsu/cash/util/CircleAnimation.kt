package com.example.jinsu.cash.util

import android.view.animation.Animation
import android.view.animation.Transformation
import com.example.jinsu.cash.activity.DrawView

class CircleAnimation : Animation {
    lateinit var circle : DrawView
    private val oldAngle: Float
    private val newAngle: Float
    private val oldAngle2: Float
    private val newAngle2: Float

    constructor(circle: DrawView, newAngle: Int, newAngle2: Int) {
        this.oldAngle = circle.getAngle1()
        this.newAngle = newAngle.toFloat()
        this.oldAngle2 = circle.getAngle2()
        this.newAngle2 = newAngle2.toFloat()
        this.circle = circle
    }

    override fun applyTransformation(interpolatedTime: Float, transformation: Transformation) {
        val angle = oldAngle + (newAngle - oldAngle) * interpolatedTime
        val angle2 = oldAngle2 + (newAngle2 - oldAngle2) * interpolatedTime

        circle.setAngle1(angle)
        circle.setAngle2(angle2)
        circle.requestLayout()
    }
}