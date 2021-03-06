package com.stacktoheap.crawler.crawler

import edu.uci.ics.crawler4j.crawler.{Page, WebCrawler}
import edu.uci.ics.crawler4j.url.WebURL
import com.stacktoheap.crawler.parser.Parser

abstract class BaseCrawler extends WebCrawler {

  val baseDomain: String
  val filters = ".*(\\.(css|js|bmp|gif|jpe?g|png|tiff?|xml|mid|mp2|mp3|mp4|wav|avi|mov|mpeg|ram|m4v|pdf|rm|smil|wmv|swf|wma|zip|rar|gz))(\\?.*)?$"
  val parser: Parser

  def shouldParse(url: String): Boolean

  override def shouldVisit(url: WebURL): Boolean = {
    val href = url.getURL.toLowerCase
    href.startsWith(baseDomain) && !href.matches(filters)
  }

  override def visit(page: Page) {
    val url = page.getWebURL.getURL
    println(s"Fetched url: $url")

    if(!shouldParse(url)) return

    println(s"Parsing url: $url")
    parser.parse(page)
  }

  override def onBeforeExit() = {
    parser.dispose()
  }
}

