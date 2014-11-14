package com.stacktoheap.crawler

import edu.uci.ics.crawler4j.crawler.Page
import edu.uci.ics.crawler4j.url.WebURL
import org.jsoup.Jsoup
import org.scalatest.mock.MockitoSugar
import org.mockito.Mockito._

trait TestUtils extends MockitoSugar { me =>
  def readFile(filename: String) = {
    io.Source
      .fromInputStream(me.getClass.getResourceAsStream(filename))
      .getLines()
      .mkString("\n")
  }

  def readDocument(filename: String) = Jsoup.parse(readFile(filename))

  def mockPage(url: String): Page = {
    val webUrl: WebURL = new WebURL()
    webUrl.setURL(url)
    val page = mock[Page]
    when(page.getWebURL).thenReturn(webUrl)
    page
  }
}
