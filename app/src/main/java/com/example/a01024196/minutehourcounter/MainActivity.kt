package com.example.a01024196.minutehourcounter

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.example.a01024196.minutehourcounter.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private val minuteCount = TrailingBucketCounter(60, 1)
    private val hourCount = TrailingBucketCounter(60, 60)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.eventButton.setOnClickListener(View.OnClickListener {
            add(100)
        })

        val handler = Handler()
        var r: Runnable = Runnable {  }
        r = Runnable {
            // 表示を更新
            binding.minuteCountTextView.text = minuteCount().toString()
            binding.hourCountTextView.text = hourCount().toString()
            handler.postDelayed(r, 10)
        }
        handler.post(r)
    }

    fun add(count: Int) {
        val now = Calendar.getInstance().timeInMillis
        minuteCount.add(count, now)
        hourCount.add(count, now)
    }

    fun minuteCount() :Int {
        val now = Calendar.getInstance().timeInMillis
        return minuteCount.trailingCount(now)
    }

    fun hourCount(): Int {
        val now = Calendar.getInstance().timeInMillis
        return hourCount.trailingCount(now)
    }


}
