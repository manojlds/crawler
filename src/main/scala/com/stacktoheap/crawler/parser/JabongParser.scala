package com.stacktoheap.crawler.parser

import com.stacktoheap.crawler.model.Product
import org.jsoup.nodes.Document
import com.stacktoheap.crawler.utilities.Utils.Managed

class JabongParser extends Parser {
  override val resultsFileName = "jabong-results"

  override def parseDocument(document: Document, url: String): Option[Product] = {
    val openGraphType = Managed(document.select("meta[property=og:type]").attr("content")).getOrElse("")
    if(openGraphType != "product") return None

    val name = Managed(document.select("meta[property=og:title]").attr("content")).getOrElse("")
    val description = Managed(document.select("meta[property=og:description]").attr("content")).getOrElse("")
    val listPrice = Managed(document.select("#price_container #product_price").text().toDouble).getOrElse(0.0)
    val sellingPrice = Managed(document.select("#price_container #product_special_price").text().toDouble).getOrElse(listPrice)

    val product = Product(name, description, url, listPrice, sellingPrice)
    println(product)
    Some(product)
  }
}
