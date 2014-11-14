package com.stacktoheap.crawler.crawler

import com.stacktoheap.crawler.UnitSpec
import com.stacktoheap.crawler.parser.Parser
import edu.uci.ics.crawler4j.crawler.Page
import edu.uci.ics.crawler4j.url.WebURL
import org.mockito.Mockito._

class BaseCrawlerSpecs extends UnitSpec {
  val mockParser = mock[Parser]

  class BaseCrawlerTest extends BaseCrawler {
    override val baseDomain: String = "http://www.stacktoheap.com"

    override def shouldParse(url: String): Boolean = url.contains("parse")

    override val parser: Parser = mockParser
  }

  val baseCrawler = new BaseCrawlerTest()

  describe("BaseCrawler") {
    describe("base domain check") {
      it("should visit urls in base domain") {
        val url: WebURL = new WebURL()
        url.setURL("http://www.stacktoheap.com/page1")
        baseCrawler.shouldVisit(url) should be(true)
      }
      it("should not visit urls in base domain") {
        val url: WebURL = new WebURL()
        url.setURL("http://www.heaptostack.com/page1")
        baseCrawler.shouldVisit(url) should be(false)
      }
    }
    describe("filters check") {
      it("should visit urls that are not filtered") {
        val url: WebURL = new WebURL()
        url.setURL("http://www.stacktoheap.com/page1")
        baseCrawler.shouldVisit(url) should be(true)
      }
      it("should not visit filtered urls") {
        val url: WebURL = new WebURL()
        url.setURL("http://www.stacktoheap.com/page1.jpg")
        baseCrawler.shouldVisit(url) should be(false)
      }
      it("should not visit filtered urls with query string") {
        val url: WebURL = new WebURL()
        url.setURL("http://www.stacktoheap.com/page1.jpg?id=1")
        baseCrawler.shouldVisit(url) should be(false)
      }
    }

    describe("visit") {
      def mockPage(url: String): Page = {
        val webUrl: WebURL = new WebURL()
        webUrl.setURL(url)
        val page = mock[Page]
        when(page.getWebURL).thenReturn(webUrl)
        page
      }

      it("should not parse url") {
        val page: Page = mockPage("http://www.stacktoheap.com/page1")

        baseCrawler.visit(page)

        verify(mockParser, never()).parse(page)
      }
      it("should parse url") {
        val page: Page = mockPage("http://www.stacktoheap.com/parse/page1")

        baseCrawler.visit(page)

        verify(mockParser, times(1)).parse(page)
      }
    }
  }

}
