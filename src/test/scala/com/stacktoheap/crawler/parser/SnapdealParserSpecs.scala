package com.stacktoheap.crawler.parser

import com.stacktoheap.crawler.UnitSpec

class SnapdealParserSpecs extends UnitSpec {
  describe("SnapdealParser") {
    val parser = new SnapdealParser()
    it("should parse document to get Product") {
      val url = "http://snapdeal.com/product_sample"
      val product = parser.parseDocument(readDocument("/sample_html/snapdeal_sample.html"), url).get
      product.name should be ("Nikon AF-S NIKKOR 50mm F/1.8G Lens")
      product.description should be ("It won’t be an exaggeration to say that the lenses are the photographer’s delight. And when the lenses are light weighted and portable as Nikon 50mm 1.8G is, you as a photographer will certainly adore it. Wearing the super integrated coating of Nikon, AF-S Nikkor 50mm f.1.8G is a prime lens with a fixed focal length of 50mm.")
      product.url should be (url)
      product.sellingPrice should be (7923.0)
      product.listPrice should be (8340.0)
    }
  }
}
