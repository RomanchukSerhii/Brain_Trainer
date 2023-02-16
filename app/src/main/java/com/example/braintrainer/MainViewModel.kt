package com.example.braintrainer

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val expression = Expression()
    private var countAnswer = 0
    private var countRightAnswer = 0

    private val _countAnswerLiveData = MutableLiveData<Int>()
    val countAnswerLiveData: LiveData<Int> = _countAnswerLiveData

    private val _countRightAnswerLiveData = MutableLiveData<Int>()
    val countRightAnswerLiveData: LiveData<Int> = _countRightAnswerLiveData

    private val _timer = MutableLiveData<Int>()
    val timer: LiveData<Int> = _timer

    private val _expressionLiveData = MutableLiveData<String>()
    val expressionLiveData: LiveData<String> = _expressionLiveData

    private val _rightAnswers = MutableLiveData<List<Int>>()
    val rightAnswers: LiveData<List<Int>> = _rightAnswers

    private val _isTimerFinish = MutableLiveData(false)
    val isTimerFinish: LiveData<Boolean> = _isTimerFinish

    fun getNewExpression(userAnswer: Int) {
        _countAnswerLiveData.value = countAnswer
        _countRightAnswerLiveData.value = countRightAnswer
        _expressionLiveData.value = expression.getExpression()
        _rightAnswers.value = expression.getAnswerVariants()
        countAnswer++
        if (userAnswer == expression.getRightAnswer()) countRightAnswer++
    }

    fun startTimer() {
        val timer = object : CountDownTimer(17000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                var seconds = (millisUntilFinished / 1000).toInt()
                seconds++
                _timer.value = seconds
            }
            override fun onFinish() {
                _timer.value = 0
                _isTimerFinish.value = true
            }
        }
        timer.start()
    }

}