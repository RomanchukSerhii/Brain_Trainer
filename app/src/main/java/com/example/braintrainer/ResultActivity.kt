package com.example.braintrainer

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
    }

    companion object {
        private const val COUNT_ANSWER_KEY = "count answer"
        private const val COUNT_RIGHT_ANSWER_KEY = "count right answer"
        fun getIntent(context: Context, countAnswer: Int, countRightAnswer: Int): Intent {
            val intent = Intent(context, ResultActivity::class.java)
            intent.putExtra(COUNT_ANSWER_KEY, countAnswer)
            intent.putExtra(COUNT_RIGHT_ANSWER_KEY, countRightAnswer)
            return intent
        }
    }
}