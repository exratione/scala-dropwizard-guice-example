package com.exratione.sdgexample.mock.guice

import com.exratione.sdgexample.config.SdgExampleConfiguration
import com.google.inject.AbstractModule
import com.google.inject.Provides
import com.google.inject.Singleton
import net.codingwell.scalaguice.ScalaModule

/**
 * A base class for mock modules to define injectors that load a mix of mock and
 * real instances.
 *
 * This allows a mock configuration to be passed in.
 */
abstract class BaseMockSdgExampleModule (
  config: SdgExampleConfiguration
) extends AbstractModule
  with ScalaModule {

  /**
   * Define the various bindings for injectors built with this module.
   */
  override def configure {
    // No bindings defined. Each test must define a complete set of bindings by
    // overriding this method.
  }

  @Provides
  @Singleton
  def configuration: SdgExampleConfiguration = config
}
