package com.example.news.extension

fun Array<Int>.findMiddleIndex(): String {
    var sum = 0
    var leftsum = 0
    for (x in this) sum += x
    for (i in 0 until this.size) {
        if (leftsum == sum - leftsum - this[i]) return "middle index is $i"
        leftsum += this[i]
    }
    return "index not found"
}
