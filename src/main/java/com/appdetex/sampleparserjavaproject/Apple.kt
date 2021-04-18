package com.appdetex.sampleparserjavaproject

import com.google.gson.Gson
import org.jsoup.Jsoup

class Apple: Retriever {

    override fun matchesUrl(url: String): Boolean {
        return url.contains("apps.apple.com")
    }

    override fun getPage(url: String): List<Output> {
        return Jsoup.connect(url)
            .get()
            .getElementsByAttributeValue("type", "application/ld+json")
            .map { element -> element.data()}
            // Issues trying to 'fromJson' using a Kotlin data class, using Java class instead
            .map { data -> Gson().fromJson(data, AppleData::class.java)}
            .map { playData -> Output(playData.name, playData.description.substringBefore("\n\n"), playData.author.name, playData.offers.price, playData.aggregateRating.ratingValue)}
    }
}