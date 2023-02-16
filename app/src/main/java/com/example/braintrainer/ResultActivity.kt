package com.example.braintrainer

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.braintrainer.databinding.ActivityResultBinding
import kotlin.properties.Delegates.notNull

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private lateinit var preferences: SharedPreferences
    private var gameResult by notNull<Int>()
    private var maxResult by notNull<Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferences = getPreferences(Context.MODE_PRIVATE)
        gameResult = intent.getIntExtra(COUNT_ANSWER_KEY, 0)
        maxResult = preferences.getInt(PREF_MAX_RESULT, 0)
        savePreference()
        bind()
    }

    private fun savePreference() {
        if (gameResult > maxResult) maxResult = gameResult
        preferences.edit()
            .putInt(PREF_MAX_RESULT, maxResult)
            .apply()
    }

    private fun bind() {
        binding.apply {
            binding.textViewResult.text = resources.getString(R.string.result, gameResult)
            binding.textViewMaxResult.text = resources.getString(R.string.max_result, maxResult)
            binding.buttonNewGame.setOnClickListener {
                val intent = MainActivity.getIntent(this@ResultActivity)
                startActivity(intent)
            }
        }
    }

    companion object {
        private const val COUNT_ANSWER_KEY = "count answer"
        private const val PREF_MAX_RESULT = "max result"
        fun getIntent(context: Context, countAnswer: Int): Intent {
            val intent = Intent(context, ResultActivity::class.java)
            intent.putExtra(COUNT_ANSWER_KEY, countAnswer)
            return intent
        }
    }
}