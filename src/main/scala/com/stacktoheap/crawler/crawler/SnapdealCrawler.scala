package com.stacktoheap.crawler.crawler

import com.stacktoheap.crawler.parser.SnapdealParser

class SnapdealCrawler extends BaseCrawler {
  override val baseDomain = "http://www.snapdeal.com"
  override val parser = new SnapdealParser

  override def shouldParse(url: String): Boolean = url.matches(s"$baseDomain/product/.*")
}
