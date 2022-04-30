package com.example.news.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class NewsModel(
    @SerializedName("author")
    @Expose
    var author: String? = null,
    @SerializedName("title")
    @Expose
    var title: String? = null,
    @SerializedName("description")
    @Expose
    var description: String? = null,
    @SerializedName("url")
    @Expose
    var url: String? = null,
    @SerializedName("urlToImage")
    @Expose
    var urlToImage: String? = null,
    @SerializedName("publishedAt")
    @Expose
    var publishedAt: String? = null,
    @SerializedName("content")
    @Expose
    var content: String? = null,
    @SerializedName("source")
    @Expose
    var source: SourceModel? = null
) : Parcelable