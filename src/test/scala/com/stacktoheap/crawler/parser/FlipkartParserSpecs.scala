package com.stacktoheap.crawler.parser

import com.stacktoheap.crawler.UnitSpec

class FlipkartParserSpecs extends UnitSpec {
  describe("FlipkartParse") {
    val parser = new FlipkartParser()
    it("should parse document to get Product") {
      val url = "http://flipkart.com/product_sample"
      val product = parser.parseDocument(readDocument("/flipkart/flipkart_sample.html"), url)
      product.name should be ("Mitashi Game In Infrazone NX")
      product.description should be ("This gaming console from Mitashi is an ideal companion for casual gamers and young kids especially pre-teens. The Infrazone NX console is a user-friendly device packed with fun features, all at an affordable cost. You are given the freedom to choose from 51 exciting inbuilt games that will put your alertness and reflexes to the test. These games play with 16-bit graphics that are visually appealing.")
      product.url should be (url)
      product.salePrice should be (1372.0)
      product.listPrice should be (1499.0)
    }
  }
}
