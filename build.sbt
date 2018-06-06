import Dependencies._

dockerRepository := Some("$group$")
maintainer in Docker := "$maintainer$"
packageSummary in Docker := "$name$"
packageName in Docker := "$name_normalized$"
packageDescription := "$name$"
dockerBaseImage := "anapsix/alpine-java:9"

enablePlugins(DockerPlugin, JavaAppPackaging)

javaOptions in Universal ++= Seq(
  "-J-XX:+UnlockExperimentalVMOptions",
  "-J-XX:+UseCGroupMemoryLimitForHeap"
)

mainClass in `root` in Compile := (mainClass in `$name_lower_camelcased$` in Compile).value
fullClasspath in `root` in Runtime ++= (fullClasspath in `$name_lower_camelcased$` in Runtime).value

lazy val root = (project in file(".")).
  aggregate($name_lower_camelcased$).
  dependsOn($name_lower_camelcased$).
  settings(inThisBuild(List(
      organization := "$group$",
      scalaVersion := "$scala_version$",
      version      := Version.version,
      scapegoatVersion := "1.3.5"
    )),
    name := "$name;format="normalize"$-root"
  )

lazy val $name_lower_camelcased$ = (project in file("$name_normalized$")).
  configs(IntegrationTest).
  settings(Defaults.itSettings).
  settings(
    name := "$name;format="normalize"$",
    libraryDependencies ++= ProjectDependencies
  )
