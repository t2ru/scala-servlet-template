lazy val root = (project in file("."))
  .settings(
    name := "hello",
    version := "0.0",
    scalaVersion := "2.11.7")
  .settings(org.scalatra.sbt.ScalatraPlugin.scalatraWithJRebel: _*)
  .settings(
    libraryDependencies += "org.scalaz" %% "scalaz-core" % "7.0.6",
    libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.4.0")
  .settings(
    libraryDependencies += "org.scalatra" %% "scalatra" % "2.3.0",
    libraryDependencies += "org.scalatra" %% "scalatra-specs2" % "2.3.0" % "test",
    libraryDependencies += "org.eclipse.jetty" % "jetty-webapp" % "9.3.5.v20151012" % "container;provided",
    libraryDependencies += "javax.servlet" % "javax.servlet-api" % "3.1.0" % "container;provided;test",
    libraryDependencies += "com.typesafe.slick" %% "slick" % "3.1.0",
    libraryDependencies += "com.h2database" % "h2" % "1.4.190")
  .enablePlugins(SbtTwirl)

