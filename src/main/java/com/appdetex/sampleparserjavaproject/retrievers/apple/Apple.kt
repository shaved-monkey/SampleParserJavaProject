package com.appdetex.sampleparserjavaproject.retrievers.apple

import com.appdetex.sampleparserjavaproject.retrievers.BasicInfo
import com.appdetex.sampleparserjavaproject.retrievers.Retriever
import com.google.gson.Gson
import org.jsoup.Jsoup
import java.text.NumberFormat
import java.util.*

class Apple: Retriever {

    private val format = NumberFormat.getCurrencyInstance(Locale.getDefault())

    private fun getPrice(price: Double, currency: String): String {
        format.currency = Currency.getInstance(currency)
        return format.format(price)
    }

    override fun compatibleWith(url: String): Boolean {
        return url.contains("apps.apple.com")
    }

    override fun extractFrom(url: String): List<BasicInfo> {
        return Jsoup.connect(url)
            .get()
            .getElementsByAttributeValue("type", "application/ld+json")
            .map { element -> element.data()}
            // Issues trying to 'fromJson' using a Kotlin data class, using Java class instead
            .map { data -> Gson().fromJson(data, AppleData::class.java)}
            .map { playData -> BasicInfo(playData.name,
                playData.description.substringBefore("\n\n"),
                playData.author.name,
                getPrice(playData.offers.price, playData.offers.priceCurrency),
                playData.aggregateRating.ratingValue)
            }
    }
}