package com.example.threadrunnable

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint
import android.os.Handler
import android.os.Message

import com.example.threadrunnable.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityMainBinding
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        binding.button.setOnClickListener {
//            val runnable = Worker()
//            val thread = Thread(runnable)
//            thread.start()
//        }
//    }
//    inner class Worker : Runnable {
//        override fun run() {
//            killSomeTime()
//        }
//    }
//    private fun killSomeTime() {
//        for (i in 1..20) {
//            Thread.sleep(2000)
//            println("i: $i")
//        }
//    }

//    private lateinit var binding: ActivityMainBinding
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        binding.button.setOnClickListener {
//            Thread {
//                killSomeTime()
//            }.start()
//        }
//    }
//    private fun killSomeTime() {
//        for (i in 1..20) {
//            runOnUiThread {
//                binding.textView.text = i.toString()
//            }
//            Thread.sleep(2000)
//            println("i: $i")
//        }
//    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var mHandler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mHandler = MyHandler()
        binding.button.setOnClickListener {
            Thread {
                killSomeTime()
            }.start()
        }
    }
    @SuppressLint("HandlerLeak")
    inner class MyHandler : Handler() {
        override fun handleMessage(msg: Message) {
            binding.textView.text = msg.data?.getString("counter")
        }
    }
    private fun killSomeTime() {
        for (i in 1..20) {
            val msg = Message.obtain()
            msg.data.putString("counter", i.toString())
            mHandler.sendMessage(msg)
            Thread.sleep(2000)
            println("i: $i")
        }
    }
}