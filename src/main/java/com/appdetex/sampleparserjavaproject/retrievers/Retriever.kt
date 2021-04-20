package com.appdetex.sampleparserjavaproject.retrievers

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException

interface Retriever {
    fun compatibleWith(url: String): Boolean
    fun extractFrom(url: String): List<BasicInfo>

    val retries: Int
    val delayInSeconds: Long
    fun fetch(url:String): Document? {
        var doc: Document? = null
        for (n in 1..retries) {
            try {
                doc = Jsoup.connect(url).get()
                break
            } catch (e: IOException) {
                println(e.localizedMessage)
                Thread.sleep(delayInSeconds * 1000)
            }
        }
        return doc
    }
}