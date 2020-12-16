# ScalaDL Ideomatic Scala Wrapper Around Deep Learning Framework JDL

Deep learning is dominated by excellent Python frameworks.
There is a new framework [JDL](https://github.com/awslabs/djl) that is a Java wrapper around several of those: PyTorch and TensorFlow.

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
sbt "run ~/Pictures/laptop_baby.jpg"

# Run with default image and engine with engine index 1
sbt "run src/test/resources/dog_bike_car.jpg 1"
```

## Output

```
Available engines:
PyTorch
MXNet
TensorFlow

prediction: [
class: "laptop", probability: 0.96828, bounds: [x=0.145, y=0.088, width=0.830, height=0.915]
class: "person", probability: 0.92875, bounds: [x=-0.013, y=0.021, width=0.458, height=0.978]
]
Detected image from ~/Pictures/laptop_baby.jpg saved to: build/output
```

![Detected objects](src/test/resources/detected-laptop_baby.jpg?raw=true "Detected objects")

# Issues

MXNet and PyTorch is working well, but Tensorflow throws an exception.

# Libraries Etc.

Using Scala 3 / Dotty. Just for fun. Will add cross compile to Scala 2.x if useful.

# Thoughts on Abstractions

Might try to use Cats, Free Monad, Tagless Final or some of that fancy heavy artillery that you should not use in prod. If they are not useful, they will be ripped out again.

# Collaborators

If ScalaDL goes anywhere collaborate would be welcome. The goal is to make a thin wrapper.
