package com.stacktoheap.crawler.model

abstract class Model {
  def toCsv: String
}

case class Product(name: String, description: String, url: String, listPrice: Double, salePrice: Double) extends Model {
  override def toCsv: String = productIterator.mkString(",")
}