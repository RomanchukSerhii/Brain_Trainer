package com.example.braintrainer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.braintrainer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        observeViewModels()

        binding.apply {
            var answer: Int

            textViewFirstVariant.setOnClickListener {
                answer = textViewFirstVariant.text.toString().toInt()
                viewModel.getNewExpression(answer)
            }

            textViewSecondVariant.setOnClickListener {
                answer = textViewSecondVariant.text.toString().toInt()
                viewModel.getNewExpression(answer)
            }

            textViewThirdVariant.setOnClickListener {
                answer = textViewThirdVariant.text.toString().toInt()
                viewModel.getNewExpression(answer)
            }

            textViewFourthVariant.setOnClickListener {
                answer = textViewThirdVariant.text.toString().toInt()
                viewModel.getNewExpression(answer)
            }
        }
        viewModel.getNewExpression()
        viewModel.startTimer()
    }

    private fun observeViewModels() {
        viewModel.apply {
            binding.apply {
                var countAnswer = 0
                var countRightAnswer = 0
                countAnswerLiveData.observe(this@MainActivity) {
                    textViewAnswersCount.text =
                        resources.getString(R.string.answers_count, it)
                    countRightAnswer = it
                }

                countRightAnswerLiveData.observe(this@MainActivity) {
                    textViewCountRightAnswers.text =
                        resources.getString(R.string.count_right_answers, it)
                    countAnswer = it
                }

                timer.observe(this@MainActivity) { second ->
                    val textSecond = if (second >= 10) {
                        second.toString()
                    } else {
                        val color = ContextCompat
                            .getColor(this@MainActivity, android.R.color.holo_red_light)
                        binding.textViewTimer.setTextColor(color)
                        "0$second"
                    }
                    binding.textViewTimer.text = resources.getString(R.string.timer, textSecond)
                }

                expressionLiveData.observe(this@MainActivity) {
                    textViewExpression.text = it
                }

                rightAnswers.observe(this@MainActivity) { rightAnswers ->
                    textViewFirstVariant.text = rightAnswers[0].toString()
                    textViewSecondVariant.text = rightAnswers[1].toString()
                    textViewThirdVariant.text = rightAnswers[2].toString()
                    textViewFourthVariant.text = rightAnswers[3].toString()
                }

                isTimerFinish.observe(this@MainActivity) { isFinish ->
                    if (isFinish) {
                        val intent = ResultActivity
                            .getIntent(this@MainActivity, countAnswer, countRightAnswer)
                        startActivity(intent)
                    }
                }
            }
        }
    }
}