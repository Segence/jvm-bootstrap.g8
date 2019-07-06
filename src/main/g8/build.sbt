import Dependencies._
import Resolvers._

$if(package_docker.truthy)$
dockerRepository := Some("$group$")
maintainer in Docker := "$maintainer$"
packageSummary in Docker := "$name$"
packageDescription := "$name$"
packageName in Docker := "$name;format="normalize"$"

dockerBaseImage := "openjdk:8-jre-alpine"

enablePlugins(DockerPlugin, JavaAppPackaging, AshScriptPlugin)

javaOptions in Universal ++= Seq(
$if(open_jmx_port.truthy)$
  "-J-XX:+UnlockExperimentalVMOptions",
  "-J-XX:+UseCGroupMemoryLimitForHeap",
  "-Dcom.sun.management.jmxremote",
  "-Dcom.sun.management.jmxremote.port=9010",
  "-Dcom.sun.management.jmxremote.rmi.port=9010",
  "-Dcom.sun.management.jmxremote.local.only=false",
  "-Dcom.sun.management.jmxremote.authenticate=false",
  "-Dcom.sun.management.jmxremote.ssl=false",
  "-Djava.rmi.server.hostname=127.0.0.1",
$endif$
  "-J-XX:+UnlockExperimentalVMOptions",
  "-J-XX:+UseCGroupMemoryLimitForHeap"
)
$endif$

mainClass in `root` in Compile := (mainClass in `$name;format="camel"$` in Compile).value
fullClasspath in `root` in Runtime ++= (fullClasspath in `$name;format="camel"$` in Runtime).value

resolvers in ThisBuild += ProjectResolvers

lazy val root = (project in file(".")).
  aggregate($name;format="camel"$).
  dependsOn($name;format="camel"$).
  settings(inThisBuild(List(
      organization := "$group$",
      scalaVersion := "$scala_version$",
      version      := Version.version,
      scapegoatVersion := "1.3.5"
    )),
    name := "$name;format="normalize"$-root"
  )

lazy val $name;format="camel"$ = (project in file("$name;format="normalize"$")).
  configs(IntegrationTest).
  settings(Defaults.itSettings).
  settings(
    name := "$name;format="normalize"$",
    libraryDependencies ++= ProjectDependencies
  )
