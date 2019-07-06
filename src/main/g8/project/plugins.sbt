$if(package_docker.truthy)$
addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.3.24")
$endif$
addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.6.0")
addSbtPlugin("com.github.mwz" % "sbt-sonar" % "2.0.0")
addSbtPlugin("com.sksamuel.scapegoat" %% "sbt-scapegoat" % "1.0.9")
