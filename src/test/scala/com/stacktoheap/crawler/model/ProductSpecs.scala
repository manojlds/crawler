package com.stacktoheap.crawler.model

import com.stacktoheap.crawler.UnitSpec

class ProductSpecs extends UnitSpec {
  describe("Product") {
    val product = Product("Lumia 925", "Windows Phone", "http://www.microsoft.com/en/mobile/phone/lumia925/", 350, 300)
    it("should give csv row") {
      product.toCsv should be("Lumia 925,Windows Phone,http://www.microsoft.com/en/mobile/phone/lumia925/,350.0,300.0")
    }
  }
}
