package com.leokuyper.turingtrivia

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leokuyper.turingtrivia.data.Question

class GameViewModel : ViewModel(){

    private val questions = listOf<Question>(
        Question("TEst 2", listOf("1", "2", "3", "4"), "2"),
        Question("TEst 1", listOf("1", "2", "3", "4"), "1"),
        Question("TEst 4", listOf("1", "2", "3", "4"), "4"),
        Question("TEst 2", listOf("1", "2", "3", "4"), "2"),
        Question("TEst 1", listOf("1", "2", "3", "4"), "1"),
        Question("TEst 4", listOf("1", "2", "3", "4"), "4"),
        Question("TEst 2", listOf("1", "2", "3", "4"), "2"),
        Question("TEst 1", listOf("1", "2", "3", "4"), "1"),
        Question("TEst 4", listOf("1", "2", "3", "4"), "4"),
        Question("TEst 2", listOf("1", "2", "3", "4"), "2"),
        Question("TEst 1", listOf("1", "2", "3", "4"), "1"),
        Question("TEst 4", listOf("1", "2", "3", "4"), "4")
    )

    private val _question = MutableLiveData<Question>()
    private  val _currentQuestion = MutableLiveData<Int>()
    private val _score = MutableLiveData<Int>()
    private  val  _amountOfQuestions = MutableLiveData<Int>()

    init {
        _score.value = 0;
        _currentQuestion.value = 0;
        _question.value = questions[0];
        _amountOfQuestions.value = 3
    }

    val question: LiveData<Question> = _question
    val currentQuestion: LiveData<Int> = _currentQuestion
    val score: LiveData<Int> = _score
    val amountOfQuestions: LiveData<Int> = _amountOfQuestions

    fun updateQuestion(index: Int){
        _question.value = questions[index.plus(1)]
        _currentQuestion.value = index.plus(1)
    }

    fun checkQuestion(answer : Int){
        val validAnswer: String? = question.value?.correctAnswer
        val submittedAnswer: String? = question.value?.answers?.get(answer)
        if(submittedAnswer ==  validAnswer){
            _score.value = score.value?.plus(1)
        }else{

        }
    }
}