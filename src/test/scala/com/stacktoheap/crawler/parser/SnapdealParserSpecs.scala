package com.stacktoheap.crawler.parser

import com.stacktoheap.crawler.UnitSpec

class SnapdealParserSpecs extends UnitSpec {
  describe("SnapdealParser") {
    val parser = new SnapdealParser()
    it("should parse document to get Product") {
      val url = "http://snapdeal.com/product_sample"
      val product = parser.parseDocument(readDocument("/sample_html/snapdeal_sample.html"), url).get
      product.name should be ("Nikon AF-S NIKKOR 50mm F/1.8G Lens")
      product.description should be ("It won’t be an exaggeration to say that the lenses are the photographer’s delight")
      product.url should be (url)
      product.sellingPrice should be (7923.0)
      product.listPrice should be (8340.0)
    }
  }
}
