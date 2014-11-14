package com.stacktoheap.crawler.model

import com.stacktoheap.crawler.UnitSpec

class ProductSpecs extends UnitSpec {
  describe("Product") {
    it("should give csv row") {
      val product = Product("Lumia 925", "Windows Phone", "http://www.microsoft.com/en/mobile/phone/lumia925/", 350, 300)
      product.toCsv should be("Lumia 925,Windows Phone,http://www.microsoft.com/en/mobile/phone/lumia925/,350.0,300.0")
    }
    it("should escape commas in fields") {
      val product = Product("Lumia 925", "Windows Phone, from Microsoft", "http://www.microsoft.com/en/mobile/phone/lumia925/", 350, 300)
      product.toCsv should be("Lumia 925,\"Windows Phone, from Microsoft\",http://www.microsoft.com/en/mobile/phone/lumia925/,350.0,300.0")
    }
    it("should escape double quotes in fields") {
      val product = Product("Lumia 925", "Windows Phone, \"the best\"", "http://www.microsoft.com/en/mobile/phone/lumia925/", 350, 300)
      product.toCsv should be("Lumia 925,\"Windows Phone, \"\"the best\"\"\",http://www.microsoft.com/en/mobile/phone/lumia925/,350.0,300.0")
    }
  }
}
