package com.exratione.sdgexample.config

import com.yammer.dropwizard.config.Configuration
import javax.validation.constraints.NotNull
import org.hibernate.validator.constraints.NotEmpty

/**
 * Configuration class for the application.
 *
 * Populated by values from the YAML configuration file passed to Dropwizard
 * when the application is launched.
 */
class SdgExampleConfiguration extends Configuration {

  @NotEmpty
  var contentEncoding: String = _

  @NotEmpty
  var contentResource: String = _

  @NotNull
  var contentTrimLength: Int = _
}
