package com.stacktoheap.crawler.parser

import java.io.Writer

import com.stacktoheap.crawler.UnitSpec
import com.stacktoheap.crawler.model.Product
import org.jsoup.nodes.Document
import org.mockito.Mockito._

class ParserSpecs extends UnitSpec {
  val mockWriter = mock[Writer]
  class ParserTest extends Parser {
    override val resultsFileName: String = "test-results"

    override lazy val writer = mockWriter

    override def parseDocument(document: Document, url: String): Option[Product] = None
  }
  val parser = new ParserTest()

  describe("Parser") {
    it("should close writer on dispose") {
      parser.dispose()

      verify(mockWriter, times(1)).close()
    }
    it("should write product to csv") {
      val product: Product = Product("Product 1", "Which is great", "http://www.stacktoheap.com", 100, 90)
      parser.writeToCsv(product)

      verify(mockWriter, times(1)).append(product.toCsv)
      verify(mockWriter, times(1)).append("\n")
    }
  }

}
