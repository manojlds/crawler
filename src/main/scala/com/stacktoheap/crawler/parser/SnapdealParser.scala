package com.stacktoheap.crawler.parser

import com.stacktoheap.crawler.model.Product
import org.jsoup.nodes.Document
import com.stacktoheap.crawler.utilities.Utils.Managed


class SnapdealParser extends Parser {
  override val resultsFileName = "snapdeal-results"

  override def parseDocument(document: Document, url: String): Option[Product] = {
    val name = Managed(document.select("h1[itemprop=name]").text()).getOrElse("")
    val description = Managed(document.select(".detailssubbox div[itemprop=description] > p:nth-child(2)").text()).getOrElse("")
    val sellingPrice = Managed(document.select("#selling-price-id").text().toDouble).getOrElse(0.0)
    val listPrice = Managed(document.select("#seller-price-id").text().replace("Rs ", "").replace(",", "").toDouble).getOrElse(sellingPrice)
    val product = Product(name, description, url, listPrice, sellingPrice)

    println(product)
    Some(product)
  }
}
