package com.example.a01024196.minutehourcounter

import android.util.Log
import java.util.*

class TrailingBucketCounter(numBuckets: Int, secsPerBucket: Int) {
    private val bucktes: ConveyorQueue
    private val secsPerBucket: Int
    private var lastUpdateTime: Long

    init {
        bucktes = ConveyorQueue(numBuckets)
        this.secsPerBucket = secsPerBucket
        lastUpdateTime = Calendar.getInstance().timeInMillis
    }

    // 通過した時間バケツの数を計算してshift
    fun update(now: Long) {
        val shiftCount = ((now - lastUpdateTime) / 1000 / secsPerBucket).toInt()
        if (shiftCount == 0) {
            return
        }

        bucktes.shift(shiftCount)
        lastUpdateTime = now
    }

    fun add(count: Int, now: Long) {
        update(now)
        bucktes.addToBack(count)
    }

    fun trailingCount(now: Long) :Int {
        update(now)
        return bucktes.totalNum
    }




}