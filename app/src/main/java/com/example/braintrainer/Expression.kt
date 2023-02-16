package com.example.braintrainer

class Expression {
    private val answerVariants = MutableList(4) { 0 }
    private var rightAnswer = 0

    fun getExpression(): String {
        val firstNumber = randomNumber()
        val secondNumber = randomNumber()
        rightAnswer = firstNumber + secondNumber
        answerVariants[0] = rightAnswer
        for (index in 1..3) {
            answerVariants[index] = randomNumber(rightAnswer)
        }
        answerVariants.shuffle()
        return "$firstNumber + $secondNumber"
    }

    fun getRightAnswer(): Int {
        return rightAnswer
    }

    fun getAnswerVariants(): List<Int> {
        return answerVariants
    }

    private fun randomNumber(): Int {
        return (1..40).random()
    }

    private fun randomNumber(answer: Int): Int {
        return ((rightAnswer - 10)..(rightAnswer + 10)).random()
    }
}