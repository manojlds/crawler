package com.stacktoheap.crawler.crawler

import com.stacktoheap.crawler.UnitSpec

class FlipkartCrawlerSpecs extends UnitSpec {
  describe("FlipkartCrawler") {
    val crawler = new FlipkartCrawler()
    it("should parse product pages") {
      crawler.shouldParse("http://www.flipkart.com/product/p/something") should be (true)
    }
    it("should not parse non product pages") {
      crawler.shouldParse("http://www.flipkart.com/products-page/something") should be (false)
    }
  }
}
