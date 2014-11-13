package com.stacktoheap.crawler.parser

import com.stacktoheap.crawler.model.Product
import org.jsoup.nodes.Document
import com.stacktoheap.crawler.parser.Utils._


class FlipkartParser extends Parser {
  override val resultsFileName = "flipkart-results"

  def getDescription(document: Document): String = {
    val description = Managed(document.select("div.rpdSection > p.description").text()).getOrElse("")
    if(!description.isEmpty) return description

    Managed(document.select("div.description .description-text p:first-child").text()).getOrElse("")
  }

  override def parseDocument(document: Document, url: String): Product = {
    val name = Managed(document.select("h1[itemprop=name]").text()).getOrElse("")
    val description = getDescription(document)
    val sellingPrice = Managed(document.select("div.product-details span.selling-price").attr("data-evar48").toDouble).getOrElse(0.0)
    val listPrice = Managed(document.select("div.product-details span.price.list").text().replace("Rs. ", "").replace(",", "").toDouble).getOrElse(sellingPrice)

    val product = Product(name, description, url, listPrice, sellingPrice)
    println(product)
    product
  }
}
