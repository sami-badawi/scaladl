package org.scaladl.object_detection

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

import collection.JavaConverters._
import scala.util.Try

/** Direct port of DJL ObjectDetection
  */
class BaseObjectDetection(preferredEngine: String) {

  // Lazy init
  lazy val logger = LoggerFactory.getLogger(classOf[BaseObjectDetection])

  lazy val engines = findAllEngines()

  lazy val engineName = findEngineName(preferredEngine)
  lazy val engine = Engine.getEngine(engineName) // Currently not used anywhere
  lazy val backbone = if ("TensorFlow" == engineName) "mobilenet_v2" else "resnet50"

  lazy val criteria =
    Criteria
      .builder()
      .optApplication(Application.CV.OBJECT_DETECTION)
      .setTypes(classOf[Image], classOf[DetectedObjects])
      .optFilter("backbone", backbone)
      .optProgress(new ProgressBar())
      // .optEngine(engineName) // finding less when this is set
      .build()
  lazy val model = ModelZoo.loadModel(criteria)
  lazy val predictor = model.newPredictor()

  def findAllEngines() = {
    val engines = Engine.getAllEngines().asScala.toSeq.sorted
    println(engines.mkString("\n\nAvailable engines:\n", "\n", "\n"))
    engines
  }

  def findEngineName(wantedEngine: String): String = {
    val usedEngine = if (wantedEngine == null || wantedEngine.isBlank)
      Engine.getInstance().getEngineName()
    else {
      val wantedEngineLower = wantedEngine.toLowerCase
      val bestEngine = engines.find(e => e.toLowerCase == wantedEngineLower)
      if (bestEngine.isEmpty)
        logger.warn(s"Could not find $wantedEngineLower using default")
      bestEngine.getOrElse(Engine.getInstance().getEngineName())
    }
    println(s"Used engine $usedEngine")
    usedEngine
  }

  def saveBoundingBoxImage(
      img: Image,
      detection: DetectedObjects,
      input: String
  ) = Try {
    val inputPath = Paths.get(input)
    val inputName = inputPath.getFileName.toFile.getName
    val outputDir = Paths.get("build/output");
    Files.createDirectories(outputDir);

    // Make image copy with alpha channel because original image was jpg
    val newImage = img.duplicate(Image.Type.TYPE_INT_ARGB)
    newImage.drawBoundingBoxes(detection);

    val imagePath = outputDir.resolve("detected-" + inputName)
    // OpenJDK can't save jpg with alpha channel
    newImage.save(Files.newOutputStream(imagePath), "png")
    logger.info("Detected objects image has been saved in: {}", imagePath)
  }

  def predict(filepath: String): DetectedObjects = {
    val imageFile = Paths.get(filepath);
    val img = ImageFactory.getInstance().fromFile(imageFile)
    val detection = predictor.predict(img)
    saveBoundingBoxImage(img, detection, filepath)
    detection
  }
}

object BaseObjectDetection {
  val defaultFile = "src/test/resources/dog_bike_car.jpg"

  def main(args: Array[String]): Unit = {
    val filename = if (args.isEmpty) defaultFile else args(0)
    val preferredEngine = if (2 <= args.size) args(1) else ""
    val baseObjectDetection = BaseObjectDetection(preferredEngine)
    val prediction = baseObjectDetection.predict(filename)
    println(s"prediction: $prediction")
    println(s"Detected image from $filename saved to: build/output")
  }
}
