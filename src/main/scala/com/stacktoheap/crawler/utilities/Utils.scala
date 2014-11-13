package com.stacktoheap.crawler.utilities

object Utils {
  def Managed[R](block: => R): Option[R] = {
    try {
      Option(block)
    } catch {
      case e: Exception => {println(e); None}
    }
  }

}
