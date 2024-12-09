package com.mnewland.giftmanager.network

import com.mnewland.giftmanager.data.wish_list.WishListItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.HttpStatusException
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

suspend fun amazonParser(wishlistStr: String): List<WishListItem> = withContext(Dispatchers.IO) {
    val items = mutableListOf<WishListItem>()
    val wishlistID =
        if(wishlistStr.length >=20){
            wishlistStr.substringAfter("wishlist/ls/").substringBefore("?")
        }else{
            wishlistStr
        }
    val url = "https://www.amazon.com/hz/wishlist/printview/$wishlistID?ref_=lv_pv&filter=unpurchased&sort=default"

    try {
        val document: Document = Jsoup.connect(url).get()

        val rows = document.select("tr.g-print-view-row")
        for (row in rows) {
            val itemId = row.id().substringAfter("tableRow_")
            val title = row.select("span.a-text-bold").text()
            val price = row.select("td:nth-child(4) span").text()
            val imageUrl = row.select("img").attr("src")
            val itemUrl = "https://www.amazon.com/dp/B0CPMDT3MW/?coliid=$itemId&colid=3V4DD3EF5H3JX"

           // items.add(WishListItem(title, price, imageUrl, itemUrl, amazonSynced = true))
        }
    }catch(e: HttpStatusException){

    }



    return@withContext items
}