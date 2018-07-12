package com.example.jinsu.cash.util

class ClsName private constructor() {
    private object Holder { val INSTANCE = ClsName() }

    companion object {
        val get: ClsName by lazy { Holder.INSTANCE }
    }

}
