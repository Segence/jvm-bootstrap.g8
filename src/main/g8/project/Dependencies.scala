import sbt._

object Dependencies {

  object DependencyVersions {
$if(use_akka.truthy)$
    val AkkaHttp = "10.1.8"
    val Akka = "2.5.23"
    val AkkaLog4j = "1.6.1"
$endif$
    val Log4j = "2.10.0"

    val Kamon = "1.1.0"
    val KamonPrometheus = "1.0.0"
    val KamonJMX = "0.1.8"

    val Scalacheck = "1.14.0"
    val Scalatest = "3.0.8"
  }

  private val CompileDependencies = Seq(
$if(use_akka.truthy)$
    "com.typesafe.akka" %% "akka-http" % DependencyVersions.AkkaHttp,
    "com.typesafe.akka" %% "akka-http-spray-json" % DependencyVersions.AkkaHttp,
    "com.typesafe.akka" %% "akka-http-xml" % DependencyVersions.AkkaHttp,
    "com.typesafe.akka" %% "akka-stream" % DependencyVersions.Akka,
    "com.typesafe.akka" %% "akka-slf4j" % DependencyVersions.Akka,
    "de.heikoseeberger" %% "akka-log4j" % DependencyVersions.AkkaLog4j,
$endif$
    "org.apache.logging.log4j" % "log4j-api" % DependencyVersions.Log4j,
    "org.apache.logging.log4j" % "log4j-core" % DependencyVersions.Log4j,
    "org.apache.logging.log4j" % "log4j-slf4j-impl" % DependencyVersions.Log4j,
    "io.kamon" %% "kamon-core" % DependencyVersions.Kamon,
    "io.kamon" %% "kamon-prometheus" % DependencyVersions.KamonPrometheus,
    "io.kamon" %% "kamon-jmx-collector" % DependencyVersions.KamonJMX
  )

  private val TestDependencies = Seq(
$if(use_akka.truthy)$
    "com.typesafe.akka" %% "akka-http-testkit" % DependencyVersions.AkkaHttp,
    "com.typesafe.akka" %% "akka-testkit" % DependencyVersions.Akka,
    "com.typesafe.akka" %% "akka-stream-testkit" % DependencyVersions.Akka,
$endif$
    "org.scalatest"     %% "scalatest" % DependencyVersions.Scalatest,
    "org.scalacheck"    %% "scalacheck" % DependencyVersions.Scalacheck
  ).map( _ % Test)

  private val IntegrationTestDependencies = Seq(
$if(use_akka.truthy)$
    "com.typesafe.akka" %% "akka-http-testkit" % DependencyVersions.AkkaHttp,
$endif$
    "org.scalatest"     %% "scalatest" % DependencyVersions.Scalatest,
    "org.scalacheck"    %% "scalacheck" % DependencyVersions.Scalacheck
  ).map( _ % IntegrationTest)

  val ProjectDependencies = CompileDependencies ++ TestDependencies ++ IntegrationTestDependencies
}
