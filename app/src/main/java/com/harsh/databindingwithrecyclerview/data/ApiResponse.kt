package com.harsh.databindingwithrecyclerview.data

import com.google.gson.annotations.SerializedName
import java.util.*

class ApiResponse<T> {
    @SerializedName("status")
    val status: String = ""

    @SerializedName("totalResults")
    val totalResults: Int = 0

    @SerializedName("articles")
    val data: T? = null

    val isSuccess
        get() = status.toLowerCase(Locale.getDefault()) == "ok"
}