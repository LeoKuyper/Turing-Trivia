package com.leokuyper.turingtrivia.data

import com.google.gson.annotations.SerializedName

data class DataModel (

    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    val name: String

)