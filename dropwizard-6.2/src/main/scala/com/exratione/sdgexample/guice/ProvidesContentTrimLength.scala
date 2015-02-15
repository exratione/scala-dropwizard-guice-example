package com.exratione.sdgexample.guice

import com.exratione.sdgexample.config.SdgExampleConfiguration
import com.google.inject.name.Named
import com.google.inject.Provides

/**
 * Note that @Provides methods cannot be overridden. Putting @Provides methods
 * into traits means that they can be reused or replaced in test modules if
 * necessary.
 */
trait ProvidesContentTrimLength {

  /**
   * Not everyone wants to pass the whole configuration to every place only a
   * part of it is needed. Using a @Provides method is a way of cutting things
   * down.
   */
  @Provides
  @Named("contentTrimLength")
  def contentTrimLength (config: SdgExampleConfiguration): Int = {
    config.contentTrimLength
  }

}
