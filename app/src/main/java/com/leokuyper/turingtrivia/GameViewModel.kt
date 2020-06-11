package com.leokuyper.turingtrivia

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leokuyper.turingtrivia.data.DataModel
import com.leokuyper.turingtrivia.data.Question
import com.leokuyper.turingtrivia.retrofit.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        _amountOfQuestions.value = 5
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
            _score.value = score.value?.plus(10)
        }else{

        }
    }

    private val questions = listOf<Question>(
        Question("Which of the following ancient Near Eastern peoples still exists as a modern ethnic group?", 1, listOf("Assyrians", "Babylonians", "Hittites", "Elamites"), "Assyrians"),
        Question("The Herero genocide was perpetrated in Africa by which of the following colonial nations?", 1, listOf("Germany", "Britain", "Belgium", "France"), "Germany"),
        Question("In which year did the Invasion of Kuwait by Iraq occur?", 1, listOf("1976", "1991", "1980", "1990"), "1990"),
        Question("What was the real name of the Albanian national leader Skanderbeg?", 1, listOf("Diturak Zhulati", "Gjergj Kastrioti", "Iskander Bejko", "Mirash Krasniki"), "Gjergj Kastrioti"),
        Question("What is the oldest US state?", 1, listOf("Virginia", "Maine", "Delaware", "Rhode Island"), "Delaware"),

        Question("What are the base station trackers used for the HTC Vive called?", 2, listOf("Motion", "Lighthouse", "Constellation", "Trackers"), "Lighthouse"),
        Question("Who patented a steam engine that produced continuous rotary motion?", 2, listOf("James Watt", "Nikola Tesla", "Albert Einstein", "Alessandro Volta"), "James Watt"),
        Question("When was the Tamagotchi digital pet released?", 2, listOf("2004", "2010", "2007", "2006"), "2007"),
        Question("Which of the following cellular device companies is NOT headquartered in Asia?", 2, listOf("Samsung", "Nokia", "LG Electronics", "HTC"), "Nokia"),
        Question("Mobile hardware and software company &quot;Blackberry Limited&quot; was founded in which country?", 2, listOf("Norway", "United States of America", "United Kingdom", "Canada"), "Canada")

    )

    fun generateDummyList(size: Int): List<CategoryItem> {
        val list = ArrayList<CategoryItem>()
        for (i in 1 until size){
            val item = CategoryItem(i,"Category $i")
            list += item
        }
        return list
    }

    val categories = listOf(
        CategoryItem(1, "History"),
        CategoryItem(2, "Science: Gadgets")    )

    fun getDataApi() {
        val call: Call<List<DataModel>> = ApiClient.getClient.getCategory()
        println("Test TEst!! Top")

        println(ApiClient.getClient.getCategory())

        call.enqueue(object : Callback<List<DataModel>> {

            override fun onResponse(call: Call<List<DataModel>>?, response: Response<List<DataModel>>?) {
                println("Test TEst!!")
                println(response)
            }

            override fun onFailure(call: Call<List<DataModel>>?, t: Throwable?) {
                println("Test onFailure")
            }

        })
    }

}


