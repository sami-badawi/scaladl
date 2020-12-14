# ScalaDL ideomatic Scala wrapper around deep learning framework JDL

Deep learning is dominated by excellent Python frameworks.
There is a new framework [JDL](https://github.com/awslabs/djl) that is a Java wrapper around several of those: Pytorch and TensorFlow.

JDL has a strong Java flavor. There is an opportunity for to make a thin Scala wrapper around this.

Maybe this could be useful for Scala Spark.

# Status Vaporware

Just playing around.

# Libraries etc.

Using Scala 3 / Dotty. Just for fun. Will add cross compile to Scala 2.x if useful.

# Thoughts on abstractions

Might try to use Cats, Free Monad, Tagless Final or some of that fancy heavy artillery that you should not use in prod. If they are not useful, they will be ripped out again.

# Collaborators

If ScalaDL goes anywhere collaborate would be welcome. The goal is to make a thin wrapper.
