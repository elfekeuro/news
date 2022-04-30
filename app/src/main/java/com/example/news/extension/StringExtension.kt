package com.example.news.extension

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*


fun String.toDateUpdatedFormat(): String {
    val updatedFormat = "MMM dd, hh:mm"
    val serverFormat = "yyyy-MM-dd'T'HH:mm:SSS'Z'"
    val date = SimpleDateFormat(serverFormat, Locale.ENGLISH).parse(this)

    return SimpleDateFormat(updatedFormat, Locale.ENGLISH).format(date ?: Date())
}

fun String.isPalindrome(): Boolean {
    val strLength = this.length
    var reverseStr = ""

    for (i in strLength - 1 downTo 0) {
        reverseStr += this[i]
    }
    return this.lowercase(Locale.getDefault()) == reverseStr.lowercase(Locale.getDefault())
}

fun Array<Int>.findMiddleIndex(): String {
    var sum = 0
    var leftsum = 0
    for (x in this) sum += x //25
    for (i in 0 until this.size) {
        if (leftsum == sum - leftsum - this[i]) return "middle index is $i"
        leftsum += this[i]
    }
    return "index not found"
}
