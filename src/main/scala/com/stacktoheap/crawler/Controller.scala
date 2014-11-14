package com.stacktoheap.crawler

import com.stacktoheap.crawler.crawler.{SnapdealCrawler, JabongCrawler, FlipkartCrawler}
import edu.uci.ics.crawler4j.crawler.{CrawlController, CrawlConfig}
import edu.uci.ics.crawler4j.fetcher.PageFetcher
import edu.uci.ics.crawler4j.robotstxt.{RobotstxtConfig, RobotstxtServer}

object Controller extends App {
  val crawlStorageFolder = "/tmp/crawl"
  val numberOfCrawlers = 1

  val config = new CrawlConfig()
  config.setCrawlStorageFolder(crawlStorageFolder)
  config.setPolitenessDelay(1000)
  config.setMaxDepthOfCrawling(3)
  config.setMaxPagesToFetch(1000)
  config.setResumableCrawling(false)
  config.setUserAgentString("Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2049.0 Safari/537.36")

  if(args.length > 0) {
    val proxyHost = args(0)
    val proxyPort = args(1).toInt
    config.setProxyHost(proxyHost)
    config.setProxyPort(proxyPort)
  }

  val pageFetcher = new PageFetcher(config)
  val robotstxtConfig = new RobotstxtConfig()
  val robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher)
  val controller = new CrawlController(config, pageFetcher, robotstxtServer)

  controller.addSeed("http://www.snapdeal.com")

//  controller.start(classOf[FlipkartCrawler], numberOfCrawlers)
//  controller.start(classOf[JabongCrawler], numberOfCrawlers)
  controller.start(classOf[SnapdealCrawler], numberOfCrawlers)
}
