package com.stacktoheap.crawler.model

import org.apache.commons.lang3.StringEscapeUtils

abstract class Model {
  def toCsv: String
}

case class Product(name: String, description: String, url: String, listPrice: Double, sellingPrice: Double) extends Model {
  override def toCsv: String = productIterator.map((value: Any) => StringEscapeUtils.escapeCsv(value.toString)).mkString(",")
}