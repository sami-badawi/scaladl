# ScalaDL Ideomatic Scala Wrapper Around Deep Learning Framework JDL

Deep learning is dominated by excellent Python frameworks.
There is a new framework [JDL](https://github.com/awslabs/djl) that is a Java wrapper around several of those: Pytorch and TensorFlow.

JDL has a strong Java flavor. There is an opportunity for to make a thin Scala wrapper around this.

Maybe this could be useful for Scala Spark.

# Status Starter Project

Ported one class, ObjectDetection.java, from DJL to ObjectDetection.Scala.



# How to Run



``` sh
git clone https://github.com/sami-badawi/scaladl.git

# Run with default image
sbt run

# Run user supplied image
sbt "run ~/Pictures/person_coffee.png"

# Run with default image and engine with index 1
sbt "run src/test/resources/dog_bike_car.jpg 1"
```

## Output

```
Available engines:
PyTorch
TensorFlow

prediction: [
	class: "dog", probability: 0.96922, bounds: [x=0.162, y=0.357, width=0.250, height=0.545]
	class: "bicycle", probability: 0.66656, bounds: [x=0.152, y=0.249, width=0.570, height=0.558]
	class: "truck", probability: 0.62682, bounds: [x=0.610, y=0.131, width=0.284, height=0.167]
]
Detected image from src/test/resources/dog_bike_car.jpg saved to: build/output
```

![Detected objects](src/test/resources/detected-dog_bike_car.jpg?raw=true "Detected objects")

# Issues

PyTorch is working well, but Tensorflow throws an exception.

# Libraries Etc.

Using Scala 3 / Dotty. Just for fun. Will add cross compile to Scala 2.x if useful.

# Thoughts on Abstractions

Might try to use Cats, Free Monad, Tagless Final or some of that fancy heavy artillery that you should not use in prod. If they are not useful, they will be ripped out again.

# Collaborators

If ScalaDL goes anywhere collaborate would be welcome. The goal is to make a thin wrapper.
