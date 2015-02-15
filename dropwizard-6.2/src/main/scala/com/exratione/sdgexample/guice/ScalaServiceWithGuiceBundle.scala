package com.exratione.sdgexample.guice

import com.exratione.sdgexample.config._
import com.hubspot.dropwizard.guice.GuiceBundle
import com.yammer.dropwizard.ScalaService

/**
 * This class is needed so that the commands can still access the GuiceBundle
 * instance.
*/
abstract class ScalaServiceWithGuiceBundle extends ScalaService[SdgExampleConfiguration] {
  var guiceBundle: GuiceBundle[SdgExampleConfiguration] = null
}
