package com.exratione.sdgexample.guice

import com.exratione.sdgexample.service._
import com.google.inject.AbstractModule
import com.google.inject.Singleton
import net.codingwell.scalaguice.ScalaModule

/**
 * This determines the bindings used by an injector built with this module.
 * See: https://github.com/google/guice/wiki/GettingStarted
 *
 * Since @Provides methods can't be overridden it seems like a good idea to
 * specify them in traits so that they can be reused by a test module if
 * necessary.
 *
 * @see ProvidesContentTrimLength
 */
class SdgExampleModule extends AbstractModule
  with ScalaModule
  with ProvidesContentTrimLength {

  /**
   * Define the bindings for injectors built with this module.
   */
  override def configure {
    // This format uses the extensions provided by ScalaModule.
    bind[ContentService].to[ResourceContentService].in[Singleton]
  }
}
