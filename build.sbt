import org.scalafmt.sbt.ScalafmtPlugin._

import scala.io.Source

name := "lightning4s"

version := Source.fromFile("VERSION").getLines.mkString

scalaVersion := "2.13.1"

packageName in Universal := name.value

scalafmtOnCompile := true

addCommandAlias("testAll", ";test")
addCommandAlias("formatAll", ";scalafmt;test:scalafmt;scalafmtSbt")
addCommandAlias("compileAll", ";compile;test:compile")
