name := "joern-sample-extension"
ThisBuild/organization := "io.shiftleft"
ThisBuild/scalaVersion := "2.13.1"

enablePlugins(JavaAppPackaging)

ThisBuild/resolvers ++= Seq(
  Resolver.mavenLocal,
  Resolver.bintrayRepo("shiftleft", "maven"),
  "Sonatype OSS" at "https://oss.sonatype.org/content/repositories/public")


libraryDependencies ++= Seq(

  "org.eclipse.jgit" % "org.eclipse.jgit" % "5.7.0.202003110725-r",

  "io.shiftleft" %% "semanticcpg" % "0.11.244",
  "org.scalatest" %% "scalatest" % "3.0.8" % Test
)

ThisBuild/Compile/scalacOptions ++= Seq(
  "-Xfatal-warnings",
  "-feature",
  "-deprecation",
  "-language:implicitConversions",
)

ThisBuild/licenses := List("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0"))


