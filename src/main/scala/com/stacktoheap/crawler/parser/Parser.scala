package com.stacktoheap.crawler.parser

import java.io.{File, PrintWriter}

import com.stacktoheap.crawler.model.Product
import edu.uci.ics.crawler4j.crawler.Page
import edu.uci.ics.crawler4j.parser.HtmlParseData
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

abstract class Parser {
  val resultsFileName: String
  lazy val writer = new PrintWriter(new File(s"/tmp/crawl/results/$resultsFileName.csv"))

  def parseDocument(document: Document, url: String): Product

  def parse(page: Page): Unit = {
    val pageHtml = page.getParseData match {
      case data: HtmlParseData => data.getHtml
    }
    val document = Jsoup.parse(pageHtml)

    val product = parseDocument(document, page.getWebURL.getURL)
    writeToCsv(product)
  }

  def writeToCsv(product: Product) {
    writer.append(product.toCsv)
    writer.append("\n")
  }

  def dispose(): Unit = {
    writer.close()
  }
}

class FlipkartParser extends Parser {
  override val resultsFileName = "flipkart-results"

  def getDescription(document: Document): String = {
    var description = document.select("div.rpdSection > p.description").text()
    if(description.isEmpty)
      description = document.select("div.description .description-text p:first-child").text()
    description
  }

  override def parseDocument(document: Document, url: String): Product = {
    val name = document.select("h1[itemprop=name]").text()

    val description: String = getDescription(document)
    val sellingPrice = document.select("div.product-details span.selling-price").attr("data-evar48").toDouble
    val listPrice = document.select("div.product-details span.price.list").text().replace("Rs. ", "").replace(",", "").toDouble

    Product(name, description, url, listPrice, sellingPrice)
  }
}
