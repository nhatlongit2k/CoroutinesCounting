package com.example.coroutinescounting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.coroutines.*
import kotlin.math.log


class MainActivity : AppCompatActivity() {

    lateinit var tvNumber: TextView
    lateinit var btPlus: Button
    lateinit var btMinus: Button
    lateinit var linearLayout: LinearLayout
    var isPress: Boolean = false
    var count: Int = 0
    var job: Job? =null
    var yPress: Int =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()


        runBlocking {

            btPlus.setOnTouchListener { view, motionEvent ->
                job?.cancel()
                when(motionEvent.action){
                    MotionEvent.ACTION_DOWN -> {
                        isPress = true
                        Log.d("PRESS", "onCreate: PRESSS")
                        GlobalScope.launch(Dispatchers.Main) {
                            while (isPress == true){
                                count++
                                Log.d("PRESS", "onCreate: $count")
                                tvNumber.setText(count.toString())
                                delay(200)
                            }
                        }
                    }
                    MotionEvent.ACTION_UP -> {
                        isPress=false
                        Log.d("PRESS", "onCreate: PRESSS-UP")
                        job = GlobalScope.launch(Dispatchers.Main) {
                            delay(1000)
                            while (count!=0){
                                if(count>0){
                                    delay(300)
                                    count--
                                    Log.d("PRESS", "onCreate: Count --")
                                    tvNumber.setText(count.toString())
                                }
                                else{
                                    delay(300)
                                    count++
                                    tvNumber.setText(count.toString())
                                }
                            }
                        }
                    }
                }
                true
            }

            btMinus.setOnTouchListener { view, motionEvent ->
                job?.cancel()
                when(motionEvent.action){
                    MotionEvent.ACTION_DOWN -> {
                        isPress = true
                        Log.d("PRESS", "onCreate: PRESSS")
                        GlobalScope.launch(Dispatchers.Main) {
                            while (isPress == true){
                                count--
                                Log.d("PRESS", "onCreate: $count")
                                tvNumber.setText(count.toString())
                                delay(200)
                            }
                        }
                    }
                    MotionEvent.ACTION_UP -> {
                        isPress=false
                        Log.d("PRESS", "onCreate: PRESSS-UP")
                        job = GlobalScope.launch(Dispatchers.Main) {
                            delay(1000)
                            while (count!=0){
                                if(count>0){
                                    delay(300)
                                    count--
                                    Log.d("PRESS", "onCreate: Count --")
                                    tvNumber.setText(count.toString())
                                }
                                else{
                                    delay(300)
                                    count++
                                    tvNumber.setText(count.toString())
                                }
                            }
                        }
                    }
                }
                true
            }
        }

        linearLayout.setOnTouchListener { view, motionEvent ->
            job?.cancel()
            when(motionEvent.action){
                MotionEvent.ACTION_DOWN -> {

                    yPress = motionEvent.y.toInt()
                    isPress = true
                    Log.d("PRESS", "onCreate: PRESSS: $yPress")
                }
                MotionEvent.ACTION_MOVE -> {
                    var y = (motionEvent.y - yPress).toInt()
                    count = count - y
                    tvNumber.setText(count.toString())
                    yPress = motionEvent.y.toInt()
                }
                MotionEvent.ACTION_UP -> {
                    Log.d("PRESS", "onCreate: PRESSS-UP")
                    job = GlobalScope.launch(Dispatchers.Main) {
                        delay(1000)
                        while (count!=0){
                            if(count>0){
                                delay(300)
                                count--
                                Log.d("PRESS", "onCreate: Count --")
                                tvNumber.setText(count.toString())
                            }
                            else{
                                delay(300)
                                count++
                                tvNumber.setText(count.toString())
                            }
                        }
                    }
                }
            }

            true
        }
    }

    private fun initView() {
        tvNumber = findViewById(R.id.tvnumber)
        btMinus = findViewById(R.id.btminus)
        btPlus = findViewById(R.id.btphus)
        linearLayout = findViewById(R.id.linearLayoutnumber)
    }
}