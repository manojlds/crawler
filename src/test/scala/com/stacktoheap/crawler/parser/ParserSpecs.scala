package com.stacktoheap.crawler.parser

import java.io.Writer

import com.stacktoheap.crawler.UnitSpec
import com.stacktoheap.crawler.model.Product
import edu.uci.ics.crawler4j.parser.HtmlParseData
import org.jsoup.nodes.Document
import org.mockito.Mockito._

class ParserSpecs extends UnitSpec {
  val mockWriter = mock[Writer]
  val product: Product = Product("Product 1", "Which is great", "http://www.stacktoheap.com", 100, 90)
  class ParserTest extends Parser {
    override val resultsFileName: String = "test-results"

    override lazy val writer = mockWriter

    override def parseDocument(document: Document, url: String): Option[Product] = url match {
      case `url` if url.contains("product") => Option(product)
      case _ => None
    }
  }
  val parser = new ParserTest()

  describe("Parser") {
    it("should close writer on dispose") {
      parser.dispose()

      verify(mockWriter, times(1)).close()
    }
    it("should parse page") {
      val page = mockPage("http://www.stacktoheap.com/page1/product")
      val htmlParseData = mock[HtmlParseData]
      when(page.getParseData).thenReturn(htmlParseData)
      when(htmlParseData.getHtml). thenReturn("")

      parser.parse(page)

      verify(mockWriter, times(1)).append(product.toCsv)
      verify(mockWriter, times(1)).append("\n")

    }
  }

}
