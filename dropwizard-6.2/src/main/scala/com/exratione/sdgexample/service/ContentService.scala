package com.exratione.sdgexample.service

import com.exratione.sdgexample.config.SdgExampleConfiguration

/**
 * A service for retrieving content.
 */
abstract class ContentService {
  def getContent(): String
}
