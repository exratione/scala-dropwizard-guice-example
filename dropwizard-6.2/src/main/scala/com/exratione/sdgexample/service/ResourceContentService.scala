package com.exratione.sdgexample.service

import com.exratione.sdgexample.config.SdgExampleConfiguration
import com.google.inject.Inject
import com.google.inject.name.Named
import java.io.BufferedInputStream
import java.util.Scanner
import scala.io.Source

  /**
   * Load the content from the resource packaged with the JAR.
   *
   * The @Inject annotation tells Guice to provide these values when an instance
   * of this class is requested via the injector.
   */
class ResourceContentService @Inject() (
  // The SdgConfiguration is automatically provided by the GuiceBundle
  // integration between Dropwizard and Guice. It isn't necessary to specify
  // a Configuration mapping in SdgExampleModule.
  config: SdgExampleConfiguration,
  // We could have just pulled the contentTrimLength from the config instance,
  // but this illustrates how the @Named @Provide method in the
  // ProvidesContentTrimLength trait is used to inject the value here.
  @Named("contentTrimLength") contentTrimLength: Int
) extends ContentService {

  private var content: String = null

  override def getContent (): String = {
    if (content == null) {
      // Load content from the resource packaged with the JAR. Doing it this way
      // also works for resources outside the JAR, which is helpful for testing
      // purposes.
      val bis: BufferedInputStream = new BufferedInputStream(
        getClass().getResourceAsStream(config.contentResource)
      )
      // Scanner iterates over tokens in the stream, and in this case we
      // separate tokens using "beginning of the input boundary" (\A) thus
      // giving us only one token for the entire contents of the stream.
      // See: http://stackoverflow.com/a/5445161
      val scanner: Scanner = new Scanner(
        bis,
        config.contentEncoding
      ).useDelimiter("\\A");
      if (scanner.hasNext) {
        content = scanner.next()
      } else {
        content = ""
      }

      if (content.length > contentTrimLength) {
        content = content.substring(0, contentTrimLength)
      }
      bis.close
    }

    content
  }

}
