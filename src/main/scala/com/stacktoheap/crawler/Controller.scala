package com.stacktoheap.crawler

import com.stacktoheap.crawler.crawler.FlipkartCrawler
import edu.uci.ics.crawler4j.crawler.{CrawlController, CrawlConfig}
import edu.uci.ics.crawler4j.fetcher.PageFetcher
import edu.uci.ics.crawler4j.robotstxt.{RobotstxtConfig, RobotstxtServer}

object Controller extends App {
  val crawlStorageFolder = "/tmp/crawl"
  val numberOfCrawlers = 1
  val proxyHost = args(0)
  val proxyPort = args(1).toInt

  val config = new CrawlConfig()
  config.setCrawlStorageFolder(crawlStorageFolder)
  config.setPolitenessDelay(1000)
  config.setMaxDepthOfCrawling(2)
  config.setMaxPagesToFetch(1000)
  config.setProxyHost(proxyHost)
  config.setProxyPort(proxyPort)
  config.setResumableCrawling(false)

  val pageFetcher = new PageFetcher(config)
  val robotstxtConfig = new RobotstxtConfig()
  val robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher)
  val controller = new CrawlController(config, pageFetcher, robotstxtServer)

  controller.addSeed("http://www.flipkart.com")

  controller.start(classOf[FlipkartCrawler], numberOfCrawlers)
}
