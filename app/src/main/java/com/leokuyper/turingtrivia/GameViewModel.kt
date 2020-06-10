package com.leokuyper.turingtrivia

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leokuyper.turingtrivia.data.Question

class GameViewModel : ViewModel(){

    private val _question = MutableLiveData<Question>()
    private  val _currentQuestion = MutableLiveData<Int>()
    private val _score = MutableLiveData<Int>()
    private  val  _amountOfQuestions = MutableLiveData<Int>()
    private val _categoryQuestions = MutableLiveData<List<Question>>()

//    init {
//        _score.value = 0;
//        _currentQuestion.value = 0;
//        _question.value = questions[0];
//        _amountOfQuestions.value = 3
//    }

    fun setupGame(categoryId: Int){
        val newQuestions = questions.filter { question -> question.categoryId == categoryId }
        _categoryQuestions.postValue(newQuestions)
        _question.value = newQuestions[0]
        _currentQuestion.value = 0
        _score.value = 0
        _amountOfQuestions.value = 3
    }

    val question: LiveData<Question> = _question
    val currentQuestion: LiveData<Int> = _currentQuestion
    val score: LiveData<Int> = _score
    val amountOfQuestions: LiveData<Int> = _amountOfQuestions

    fun updateQuestion(index: Int){
//        _question.value = questions[index.plus(1)]

        _question.value = _categoryQuestions.value?.get(index.plus(1))
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

    private val questions = listOf<Question>(
        Question("TEst 2", 1, listOf("1", "2", "3", "4"), "2"),
        Question("TEst 3", 1, listOf("1", "2", "3", "4"), "3"),
        Question("TEst 2", 1, listOf("1", "2", "3", "4"), "2"),
        Question("TEst 3", 1, listOf("1", "2", "3", "4"), "3"),
        Question("TEst 2", 1, listOf("1", "2", "3", "4"), "2"),
        Question("TEst 4", 1, listOf("1", "2", "3", "4"), "4"),
        Question("TEst 2", 2, listOf("1", "2", "3", "4"), "2"),
        Question("TEst 3", 2, listOf("1", "2", "3", "4"), "3"),
        Question("TEst 2", 2, listOf("1", "2", "3", "4"), "2"),
        Question("TEst 3", 2, listOf("1", "2", "3", "4"), "3"),
        Question("TEst 2", 2, listOf("1", "2", "3", "4"), "2"),
        Question("TEst 4", 2, listOf("1", "2", "3", "4"), "4")
    )

    fun generateDummyList(size: Int): List<CategoryItem> {
        val list = ArrayList<CategoryItem>()
        for (i in 1 until size){
            val item = CategoryItem(i,"Category $i")
            list += item
        }
        return list
    }

}