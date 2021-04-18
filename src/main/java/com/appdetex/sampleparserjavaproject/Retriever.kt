package com.appdetex.sampleparserjavaproject

interface Retriever {
    fun matchesUrl(url: String): Boolean
    fun getPage(url: String): List<Output>
}