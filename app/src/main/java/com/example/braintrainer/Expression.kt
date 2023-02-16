package com.example.braintrainer

class Expression {
    private val answerVariants = MutableList(4) { 0 }
    private var rightAnswer = 0

    fun getExpression(): String {
        val firstNumber = randomNumber()
        val secondNumber = randomNumber()
        rightAnswer = firstNumber + secondNumber
        answerVariants[0] = rightAnswer
        addRandomVariants()
        answerVariants.shuffle()
        return "$firstNumber + $secondNumber"
    }

    fun getRightAnswer(): Int {
        return rightAnswer
    }

    fun getAnswerVariants(): List<Int> {
        return answerVariants
    }

    private fun addRandomVariants() {
        for (index in 1..3) {
            var randomAnswer = randomNumber(rightAnswer)
            while (answerVariants.contains(randomAnswer) && randomAnswer < 0) {
                randomAnswer = randomNumber(rightAnswer)
            }
            answerVariants[index] = randomAnswer
        }
    }

    private fun randomNumber(): Int {
        return (1..40).random()
    }

    private fun randomNumber(answer: Int): Int {
        return ((rightAnswer - 10)..(rightAnswer + 10)).random()
    }
}