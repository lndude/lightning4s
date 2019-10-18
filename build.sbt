import scala.io.Source

name := "lightning4s"

version := Source.fromFile("VERSION").getLines.mkString

scalaVersion := "2.13.1"

packageName in Universal := name.value

scalafmtOnCompile := true


lazy val all = (project in file("."))
  .settings(
    libraryDependencies ++= coreDependencies
  )
  .aggregate(
    core,
    lnd,
    cLightning,
    eclair
  )

lazy val core = (project in file("core")).settings(
  libraryDependencies ++= coreDependencies
)

lazy val lnd = (project in file("lnd"))
  .settings(
    libraryDependencies ++= coreDependencies
  )
  .dependsOn(
    core
  )

lazy val cLightning = (project in file("c-lightning"))
  .settings(
    libraryDependencies ++= coreDependencies
  )
  .dependsOn(
    core
  )

lazy val eclair = (project in file("eclair"))
  .settings(
    libraryDependencies ++= coreDependencies
  )
  .dependsOn(
    core
  )


lazy val dependencies = new {
  val akkaHttpVersion = "10.1.9"
  val akkaVersion = "2.5.25"
  val logbackVersion = "1.3.0-alpha4"
  val catsVersion = "1.6.1"
  val sttpVersion = "1.6.6"
  val scalatestVersion = "3.0.8"
  val pureconfigVersion = "0.11.1"
  val scalaLoggingVersion = "3.9.2"

  val test = Seq(
    "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion  % Test,
    "com.typesafe.akka" %% "akka-testkit"      % akkaVersion      % Test,
    "org.scalatest"     %% "scalatest"         % scalatestVersion % Test
  )

  val akka = Seq(
    "com.typesafe.akka" %% "akka-http"            % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-slf4j"           % akkaVersion,
    "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-stream"          % akkaVersion
  )

  val http = Seq(
    "com.softwaremill.sttp" %% "core"              % sttpVersion,
    "com.softwaremill.sttp" %% "akka-http-backend" % sttpVersion
  )

  val logging = Seq(
    "ch.qos.logback"             % "logback-classic" % logbackVersion,
    "com.typesafe.scala-logging" %% "scala-logging"  % scalaLoggingVersion
  )

  val cats = Seq("org.typelevel" %% "cats-core" % catsVersion)

  val pureConfig = Seq("com.github.pureconfig" %% "pureconfig" % pureconfigVersion)
}


lazy val coreDependencies = Seq(
  dependencies.logging,
  dependencies.akka,
  dependencies.test
).flatten

lazy val settings =
  coreSettings

lazy val coreSettings = Seq(
  scalafmtOnCompile := true
)

addCommandAlias("testAll", ";test")
addCommandAlias("formatAll", ";scalafmt;test:scalafmt;scalafmtSbt")
addCommandAlias("compileAll", ";compile;test:compile")
