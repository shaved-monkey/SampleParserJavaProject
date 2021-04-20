package com.appdetex.sampleparserjavaproject.retrievers.googleplay

import com.appdetex.sampleparserjavaproject.retrievers.BasicInfo
import com.appdetex.sampleparserjavaproject.retrievers.Retriever
import com.google.gson.Gson
import org.jsoup.nodes.Document
import java.text.NumberFormat
import java.util.*
import kotlin.math.roundToInt

class GooglePlay: Retriever {

    private val format = NumberFormat.getCurrencyInstance(Locale.getDefault())

    private fun getPrice(price: Double, currency: String): String {
        format.currency = Currency.getInstance(currency)
        return format.format(price)
    }

    override fun compatibleWith(url: String): Boolean {
        return url.contains("play.google.com")
    }

    override val retries: Int = 2
    override val delayInSeconds: Long = 2

    override fun extractFrom(url: String): List<BasicInfo> {
        val doc = fetch(url)
        return if (doc == null) {
            println("Unable to fetch $url")
            Collections.emptyList()
        } else {
            parse(doc)
        }
    }

    fun parse(doc: Document): List<BasicInfo> {
        return doc.getElementsByAttributeValue("type", "application/ld+json")
            .map { element -> element.data()}
            // Issues trying to 'fromJson' using a Kotlin data class, using Java class instead
            .map { data -> Gson().fromJson(data, PlayData::class.java)}
            .map { playData -> BasicInfo(playData.name,
                // using /n/n to determine paragraphs
                playData.description.substringBefore("\n\n"),
                playData.author.name,
                // Proper currency formatting
                getPrice(playData.offers[0].price, playData.offers[0].priceCurrency),
                // rounding to tenths place
                (playData.aggregateRating.ratingValue * 10.0).roundToInt() /10.0)
            }
    }

}