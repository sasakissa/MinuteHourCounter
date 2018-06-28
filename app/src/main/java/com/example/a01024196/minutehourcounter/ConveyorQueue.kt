package com.example.a01024196.minutehourcounter

import android.util.Log

class ConveyorQueue(maxItem: Int) {
    private val maxItem: Int
    private var queue: MutableList<Int> = mutableListOf()
    var totalNum: Int = 0
    init {
        this.maxItem = maxItem
    }

    fun shift(numShifted: Int) {
        // 項目がシフトされすぎた場合、キューをクリアする
        if (numShifted > maxItem) {
            queue = mutableListOf()
            totalNum = 0
            return
        }

        // 必要な数だけ0をプッシュする
        for (i in 0 until numShifted) {
            queue.add(0)
        }

        // 経過した項目は全て落とす
        while (queue.size > maxItem) {
            totalNum -= queue[0]
            queue.removeAt(0)
        }

    }

    fun addToBack(count: Int) {
        if (queue.isEmpty()) {
            queue.add(1)
            return
        }

        queue[queue.lastIndex] = queue[queue.lastIndex] + count
        totalNum += count
        Log.d("count", queue.toString())
    }
}