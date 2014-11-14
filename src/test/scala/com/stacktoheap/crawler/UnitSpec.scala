package com.stacktoheap.crawler

import org.scalamock.proxy.ProxyMockFactory
import org.scalamock.scalatest.MockFactory
import org.scalatest._
import org.jsoup.Jsoup
import org.scalatest.mock.MockitoSugar

trait TestUtils { me =>
  def readFile(filename: String) = {
    io.Source
      .fromInputStream(me.getClass.getResourceAsStream(filename))
      .getLines()
      .mkString("\n")
  }

  def readDocument(filename: String) = Jsoup.parse(readFile(filename))
}

abstract class UnitSpec extends FunSpec with Matchers with MockitoSugar
with OptionValues with Inside with Inspectors with TestUtils