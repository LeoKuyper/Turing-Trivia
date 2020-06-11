package com.leokuyper.turingtrivia.data

data class Question(
    val question: String,
    val categoryId: Int,
    val answers: List<String>,
    val correctAnswer: String
)
