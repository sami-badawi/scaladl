package org.scaladl

import org.junit.Test
import org.junit.Assert._

class HelloTest {
  @Test def t1(): Unit = {
    assertEquals("I was compiled by Scala 3. :)", msg)
  }
}