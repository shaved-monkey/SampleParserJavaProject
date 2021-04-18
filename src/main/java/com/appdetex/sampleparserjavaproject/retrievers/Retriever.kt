package com.appdetex.sampleparserjavaproject.retrievers

interface Retriever {
    fun compatibleWith(url: String): Boolean
    fun extractFrom(url: String): List<BasicInfo>
}