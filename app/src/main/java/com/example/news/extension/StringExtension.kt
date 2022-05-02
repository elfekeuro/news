package com.example.news.extension

import java.text.SimpleDateFormat
import java.util.*


fun String.toDateUpdatedFormat(): String {
    val updatedFormat = "MMM dd, hh:mm"
    val serverFormat = "yyyy-MM-dd'T'HH:mm:SSS'Z'"
    val date = SimpleDateFormat(serverFormat, Locale.ENGLISH).parse(this)

    return SimpleDateFormat(updatedFormat, Locale.ENGLISH).format(date ?: Date())
}
