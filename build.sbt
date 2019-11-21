name := "FlashFry"

version := "1.9.9.1"

scalaVersion := "2.12.1"

resolvers ++= Seq(
  Resolver.sonatypeRepo("public"),
  Resolver.sonatypeRepo("snapshots"),
  Resolver.sonatypeRepo("releases")
)

// set to true if you want to try and build for a GPU with aparapi

unmanagedBase := (baseDirectory.value / "project" )

libraryDependencies += "com.github.samtools" % "htsjdk" % "2.8.1"

libraryDependencies += "org.scalatest" % "scalatest_2.12" % "3.2.0-SNAP4" % "test"

libraryDependencies += "com.typesafe.scala-logging" % "scala-logging_2.12" % "3.5.0"

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.2"

libraryDependencies += "info.picocli" % "picocli" % "3.8.1"

//startYear := Some(2015)

//headerLicense := Some(HeaderLicense.MIT("2015", "Aaron McKenna"))

mainClass in (Compile, packageBin) := Some("main.scala.Main")

mainClass in (Compile, run) := Some("main.scala.Main")

javaOptions += "-Xmx2G"
