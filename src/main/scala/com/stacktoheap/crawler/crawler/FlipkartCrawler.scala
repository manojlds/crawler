package com.stacktoheap.crawler.crawler

import com.stacktoheap.crawler.parser.FlipkartParser
import edu.uci.ics.crawler4j.url.WebURL

class FlipkartCrawler extends BaseCrawler {
  override val baseDomain = "http://www.flipkart.com"
  override val parser = new FlipkartParser

  override def shouldParse(url: String): Boolean = url.matches(s"$baseDomain.*/p/.*")

  override def shouldVisit(url: WebURL): Boolean = {
    super.shouldVisit(url) && shouldParse(url.getURL)
  }
}

