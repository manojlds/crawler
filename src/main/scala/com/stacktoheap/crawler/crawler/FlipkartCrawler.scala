package com.stacktoheap.crawler.crawler

import com.stacktoheap.crawler.parser.{FlipkartParser, Parser}

class FlipkartCrawler extends BaseCrawler {
  override val baseDomain = "http://www.flipkart.com"
  override val parser: Parser = new FlipkartParser
}
