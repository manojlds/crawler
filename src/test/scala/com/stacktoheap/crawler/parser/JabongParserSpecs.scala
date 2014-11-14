package com.stacktoheap.crawler.parser

import com.stacktoheap.crawler.UnitSpec

class JabongParserSpecs extends UnitSpec {
   describe("JabongParser") {
     val parser = new JabongParser()
     it("should parse document to get Product") {
       val url = "http://jabong.com/product_sample"
       val product = parser.parseDocument(readDocument("/sample_html/jabong_sample.html"), url).get
       product.name should be ("Grey Sneakers")
       product.description should be ("Create a trend that captures the essence of the season as you adorn this pair of sneakers by United Colors of Benetton. Crafted using rubber, the sole will keep your feet at comfort all day long. These lightweight shoes will look good when worn with a pair of jeans and a T-shirt.")
       product.url should be (url)
       product.sellingPrice should be (2099.0)
       product.listPrice should be (2099.0)
     }
   }
 }
