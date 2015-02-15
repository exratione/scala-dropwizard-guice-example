package com.exratione.sdgexample

import javax.xml.stream.XMLReporter
import org.junit.runner.RunWith
import org.scalatest.{OneInstancePerTest, Matchers, FlatSpec}
import org.scalamock.scalatest.MockFactory
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
abstract class SdgExampleTest extends FlatSpec
  with MockFactory
  with Matchers
  with OneInstancePerTest
