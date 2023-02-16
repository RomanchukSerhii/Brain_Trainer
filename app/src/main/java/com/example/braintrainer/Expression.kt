package com.example.braintrainer

class Expression {
    private val answerVariants = mutableListOf<Int>()
    private var rightAnswer = 0

    fun getExpression(): String {
        val firstNumber = randomNumber()
        val secondNumber = randomNumber()
        rightAnswer = firstNumber + secondNumber
        answerVariants.add(rightAnswer)
        repeat(3) {
            answerVariants.add(randomNumber(rightAnswer))
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
        return (rightAnswer - 10..rightAnswer + 10).random()
    }
}