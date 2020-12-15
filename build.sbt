val scala3Version = "3.0.0-M2"

lazy val root = project
  .in(file("."))
  .settings(
    name := "scaladl",
    version := "0.1.0",

    scalaVersion := scala3Version,

    libraryDependencies ++= Seq(
      "ai.djl" % "api" % "0.8.0",
      "ai.djl.pytorch" % "pytorch-engine" % "0.8.0",
      "ai.djl.pytorch" % "pytorch-native-auto" % "1.6.0",
      "ai.djl.pytorch" % "pytorch-model-zoo" % "0.8.0",
      "com.novocode" % "junit-interface" % "0.11" % "test"
    )
  )
