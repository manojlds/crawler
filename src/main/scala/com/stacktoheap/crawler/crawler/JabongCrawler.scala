package com.stacktoheap.crawler.crawler

import com.stacktoheap.crawler.parser.JabongParser

class JabongCrawler extends BaseCrawler {
  override val baseDomain = "http://www.jabong.com"
  override val parser = new JabongParser

  override def shouldParse(url: String): Boolean = url.matches(".*\\.html$")
}
