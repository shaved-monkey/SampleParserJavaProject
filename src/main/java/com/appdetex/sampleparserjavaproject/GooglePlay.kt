package com.appdetex.sampleparserjavaproject

import org.jsoup.Jsoup
import com.google.gson.Gson
import java.text.NumberFormat
import java.util.*

class GooglePlay: Retriever {

    private val format = NumberFormat.getCurrencyInstance(Locale.getDefault())

    private fun getPrice(price: Double, currency: String): String {
        format.currency = Currency.getInstance(currency)
        return format.format(price)
    }

    override fun matchesUrl(url: String): Boolean {
        return url.contains("play.google.com")
    }

    override fun getPage(url: String): List<Output> {
        return Jsoup.connect(url)
            .get()
            .getElementsByAttributeValue("type", "application/ld+json")
            .map { element -> element.data()}
                // Issues trying to 'fromJson' using a Kotlin data class, using Java class instead
            .map { data -> Gson().fromJson(data, PlayData::class.java)}
            .map { playData -> Output(playData.name,
                playData.description.substringBefore("\n\n"),
                playData.author.name,
                getPrice(playData.offers[0].price, playData.offers[0].priceCurrency),
                playData.aggregateRating.ratingValue)
            }
    }
}