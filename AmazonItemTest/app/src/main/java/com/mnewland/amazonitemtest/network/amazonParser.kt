package com.mnewland.amazonitemtest.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document


data class WishlistItem(
    val title: String,
    val price: String,
    val imageUrl: String,
    val itemUrl: String
)

suspend fun amazonParser(wishListID: String): List<WishlistItem> = withContext(Dispatchers.IO) {
    val url = "https://www.amazon.com/hz/wishlist/printview/$wishListID?ref_=lv_pv&filter=unpurchased&sort=default"
    val document: Document = Jsoup.connect(url).get()
    val items = mutableListOf<WishlistItem>()

    val rows = document.select("tr.g-print-view-row")
    for (row in rows) {
        val itemId = row.id().substringAfter("tableRow_")
        val title = row.select("span.a-text-bold").text()
        val price = row.select("td:nth-child(4) span").text()
        val imageUrl = row.select("img").attr("src")
        val itemUrl = "https://www.amazon.com/dp/B0CPMDT3MW/?coliid=$itemId&colid=3V4DD3EF5H3JX"

        items.add(WishlistItem(title, price, imageUrl, itemUrl))
    }

    return@withContext items
}
