package com.stacktoheap.crawler.parser

import edu.uci.ics.crawler4j.crawler.Page

abstract class Parser {
  def parse(page: Page): Unit
}

class FlipkartParser extends Parser {
  def parse(page: Page): Unit = {

  }
}
