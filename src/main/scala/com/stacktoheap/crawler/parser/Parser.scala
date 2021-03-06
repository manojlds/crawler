package com.stacktoheap.crawler.parser

import java.io.{Writer, File, PrintWriter}

import com.stacktoheap.crawler.Controller
import com.stacktoheap.crawler.model.Product
import edu.uci.ics.crawler4j.crawler.Page
import edu.uci.ics.crawler4j.parser.HtmlParseData
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

abstract class Parser {
  val resultsFileName: String
  lazy val writer: Writer = initializeResultsFile

  def initializeResultsFile: PrintWriter = {
    val resultsDirectory = Controller.crawlStorageFolder
    val file = new File(s"$resultsDirectory/$resultsFileName.csv")
    if(file.exists()) file.delete()
    file.createNewFile()

    new PrintWriter(file)
  }

  def parseDocument(document: Document, url: String): Option[Product]

  def parse(page: Page) {
    val pageHtml = page.getParseData match {
      case data: HtmlParseData => data.getHtml
    }
    val document = Jsoup.parse(pageHtml)

    parseDocument(document, page.getWebURL.getURL) map writeToCsv
  }

  def writeToCsv(product: Product) {
    writer.append(product.toCsv)
    writer.append("\n")
  }

  def dispose() {
    writer.close()
  }
}


