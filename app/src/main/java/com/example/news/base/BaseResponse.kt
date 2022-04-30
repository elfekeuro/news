package com.example.news.base

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


open class BaseResponse<T> {
    @SerializedName("status")
    @Expose
    var status: String? = null
    @SerializedName("code")
    @Expose
    var code: String? = null
    @SerializedName("totalResults")
    @Expose
    var totalResults: String? = null
    @SerializedName("message")
    @Expose
    var message: String? = null
    @SerializedName("articles")
    @Expose
    open var articles: ArrayList<T>? = null
}