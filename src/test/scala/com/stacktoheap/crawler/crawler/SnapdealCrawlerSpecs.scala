package com.stacktoheap.crawler.crawler

import com.stacktoheap.crawler.UnitSpec

class SnapdealCrawlerSpecs extends UnitSpec {
  describe("SnapdealCrawler") {
    val crawler = new SnapdealCrawler()
    it("should parse product pages") {
      crawler.shouldParse("http://www.snapdeal.com/product/something.html") should be (true)
    }
    it("should not parse non product pages") {
      crawler.shouldParse("http://www.jabong.com/products/something") should be (false)
    }
  }
}
