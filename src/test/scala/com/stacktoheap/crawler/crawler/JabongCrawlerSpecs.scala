package com.stacktoheap.crawler.crawler

import com.stacktoheap.crawler.UnitSpec

class JabongCrawlerSpecs extends UnitSpec {
  describe("JabongCrawler") {
    val crawler = new JabongCrawler()
    it("should parse product pages") {
      crawler.shouldParse("http://www.jabong.com/product-name.html") should be (true)
    }
    it("should not parse non product pages") {
      crawler.shouldParse("http://www.jabong.com/products-page/something") should be (false)
    }
  }
}
