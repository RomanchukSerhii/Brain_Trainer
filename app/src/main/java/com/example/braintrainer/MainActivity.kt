package com.example.braintrainer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import com.example.braintrainer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val timer = object : CountDownTimer(6000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                var seconds = (millisUntilFinished / 1000).toInt()
                seconds++
                binding.textViewTimer.text = seconds.toString()
            }
            override fun onFinish() {
                Toast.makeText(
                    this@MainActivity, "Timer is end", Toast.LENGTH_SHORT
                ).show()
                binding.textViewTimer.text = "0"
            }
        }
        timer.start()

    }
}