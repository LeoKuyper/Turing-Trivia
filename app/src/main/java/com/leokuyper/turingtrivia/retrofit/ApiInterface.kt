package com.leokuyper.turingtrivia.retrofit

import com.leokuyper.turingtrivia.data.DataModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("api_category.php")
    fun getCategory(): Call<List<DataModel>>
}
