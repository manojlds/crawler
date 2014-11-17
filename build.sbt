name := "crawler"

version := "1.0"

scalaVersion := "2.10.4"

scalacOptions ++= Seq("-g:vars", "-Yrangepos")

libraryDependencies ++= Seq(
  "org.apache.commons" % "commons-lang3" % "3.3.2",
  "edu.uci.ics" % "crawler4j" % "3.5",
  "org.jsoup" % "jsoup" % "1.8.1",
  "org.scalatest" % "scalatest_2.10" % "2.2.0" % Test,
  "junit" % "junit" % "4.10" % Test,
  "org.scoverage" % "scalac-scoverage-plugin_2.10" % "0.98.2" % Test,
  "org.mockito" % "mockito-all" % "1.9.0" % Test
)

instrumentSettings