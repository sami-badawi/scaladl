package org.scaladl

import ai.djl.Application
import ai.djl.ModelException
import ai.djl.engine.Engine
import ai.djl.inference.Predictor
import ai.djl.modality.cv.Image
import ai.djl.modality.cv.ImageFactory
import ai.djl.modality.cv.output.DetectedObjects
import ai.djl.repository.zoo.Criteria
import ai.djl.repository.zoo.ModelZoo
import ai.djl.repository.zoo.ZooModel
import ai.djl.training.util.ProgressBar
import ai.djl.translate.TranslateException
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Direct port of DJL ObjectDetection
 */
object ObjectDetection {
  val logger = LoggerFactory.getLogger(classOf[LoggerFactory])

  val defaultFile = "src/test/resources/dog_bike_car.jpg"

  def saveBoundingBoxImage(img: Image, detection: DetectedObjects) = {
    val outputDir = Paths.get("build/output");
    Files.createDirectories(outputDir);

    // Make image copy with alpha channel because original image was jpg
    val newImage = img.duplicate(Image.Type.TYPE_INT_ARGB)
    // val newImage = newImage1.as[Image]
    newImage.drawBoundingBoxes(detection);

    val imagePath = outputDir.resolve("detected-dog_bike_car.png")
    // OpenJDK can't save jpg with alpha channel
    newImage.save(Files.newOutputStream(imagePath), "png")
    logger.info("Detected objects image has been saved in: {}", imagePath)
  }

  def predict(filepath: String): DetectedObjects = {
    val imageFile = Paths.get(filepath);
    val img = ImageFactory.getInstance().fromFile(imageFile)

    val engineName = Engine.getInstance().getEngineName()
    val backbone =
      if ("TensorFlow" == engineName) "mobilenet_v2" else "resnet50"
    val criteria =
      Criteria
        .builder()
        .optApplication(Application.CV.OBJECT_DETECTION)
        .setTypes(classOf[Image], classOf[DetectedObjects])
        .optFilter("backbone", backbone)
        .optProgress(new ProgressBar())
        .build()
    val model = ModelZoo.loadModel(criteria)
    val predictor = model.newPredictor()
    val detection = predictor.predict(img)
    saveBoundingBoxImage(img, detection)
    detection
  }

  def main(args: Array[String]): Unit = {
    val filename =
      if (args.isEmpty)
        defaultFile
      else
        args(0)
    val prediction = predict(filename)
    println(s"prediction: $prediction")
    println(s"Detected image from $filename saved to: build/output")
  }
}
