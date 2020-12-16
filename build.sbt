val scala3Version = "3.0.0-M2"

lazy val root = project
  .in(file("."))
  .settings(
    name := "scaladl",
    version := "0.1.0",

    scalaVersion := scala3Version,

    libraryDependencies ++= Seq(
      "ai.djl" % "api" % "0.8.0",

      "ai.djl.mxnet" % "mxnet-engine" % "0.8.0" % "runtime",
      "ai.djl.mxnet" % "mxnet-model-zoo" % "0.8.0",
      "ai.djl.mxnet" % "mxnet-native-auto" % "1.7.0-b",
      "ai.djl.mxnet" % "mxnet-native-cu92mkl" % "1.7.0-b",

      "ai.djl.pytorch" % "pytorch-engine" % "0.8.0",
      "ai.djl.pytorch" % "pytorch-model-zoo" % "0.8.0",
      "ai.djl.pytorch" % "pytorch-engine" % "0.8.0",
      "ai.djl.pytorch" % "pytorch-native-auto" % "1.6.0",

      "ai.djl.tensorflow" % "tensorflow-api" % "0.8.0",
      "ai.djl.tensorflow" % "tensorflow-engine" % "0.8.0",
      "ai.djl.tensorflow" % "tensorflow-model-zoo" % "0.8.0",
      "ai.djl.tensorflow" % "tensorflow-native-cu101" % "2.3.1",
      "ai.djl.tensorflow" % "tensorflow-native-auto" % "2.3.1",
      "ai.djl.tensorflow" % "tensorflow-native-cpu" % "2.3.1",

      "com.novocode" % "junit-interface" % "0.11" % "test"
    )
  )
