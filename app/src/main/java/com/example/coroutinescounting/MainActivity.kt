package com.example.coroutinescounting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {

    lateinit var tvNumber: TextView
    lateinit var btPlus: Button
    lateinit var btMinus: Button
    var count: Int = 0
    var job: Job? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()


        runBlocking {
            btPlus.setOnClickListener {
                count++
                tvNumber.setText(count.toString())
                job?.cancel()
                job = GlobalScope.launch(Dispatchers.Main) {
                    delay(1000)
                    while (count!=0){
                        if(count>0){
                            delay(500)
                            count--
                            tvNumber.setText(count.toString())
                        }
                        else{
                            delay(500)
                            count++
                            tvNumber.setText(count.toString())
                        }
                    }
                }
            }
            btMinus.setOnClickListener {
                count--
                tvNumber.setText(count.toString())
                job?.cancel()
                job = GlobalScope.launch(Dispatchers.Main) {
                    delay(1000)
                    while (count!=0){
                        if(count>0){
                            delay(500)
                            count--
                            tvNumber.setText(count.toString())
                        }
                        else{
                            delay(500)
                            count++
                            tvNumber.setText(count.toString())
                        }
                    }
                }
            }
        }

    }

    private fun initView() {
        tvNumber = findViewById(R.id.tvnumber)
        btMinus = findViewById(R.id.btminus)
        btPlus = findViewById(R.id.btphus)
    }
}