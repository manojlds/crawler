package com.stacktoheap.crawler

import org.scalatest._
import org.scalatest.mock.MockitoSugar

abstract class UnitSpec extends FunSpec with Matchers with MockitoSugar
with OptionValues with Inside with Inspectors with TestUtils