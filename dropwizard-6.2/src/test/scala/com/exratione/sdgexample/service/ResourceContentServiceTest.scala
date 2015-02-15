package com.exratione.sdgexample.service

import com.exratione.sdgexample.config.SdgExampleConfiguration
import com.exratione.sdgexample.guice._
import com.exratione.sdgexample.mock.guice._
import com.exratione.sdgexample.SdgExampleTest
import com.exratione.sdgexample.service._
import com.google.inject.AbstractModule
import com.google.inject.Guice
import com.google.inject.Provides
import com.google.inject.Singleton
import net.codingwell.scalaguice.InjectorExtensions._
import net.codingwell.scalaguice.ScalaModule

class ResourceContentServiceTest extends SdgExampleTest {

  // ------------------------------------------------------------------------
  // Build an injector.
  // ------------------------------------------------------------------------

  // Every test set for a distinct class is probably going to want its own
  // distinct injector, mixing and matching mocks with real classes. This can
  // become tedious, but since @Provide methods cannot be overridden there is
  // usually not much to be gained by trying to build a hierarchy of mocks based
  // on the actual application configuration.

  /**
   * A mock module for use with the injector for this test suite.
   */
  class MockSdgExampleModule (config: SdgExampleConfiguration)
    extends BaseMockSdgExampleModule (config)
    with ProvidesContentTrimLength {

    /**
     * Define the various bindings for injectors built with this module.
     */
    override def configure {
      // If there were more classes in this example, this method would define a
      // mix of bindings to mock and real instances depending on the details of
      // what was being tested.
      bind[ContentService].to[ResourceContentService].in[Singleton]
    }
  }

  val mockSdgExampleConfiguration = new SdgExampleConfiguration
  mockSdgExampleConfiguration.contentEncoding = "UTF-8"
  mockSdgExampleConfiguration.contentResource = "/resources/content.txt"
  mockSdgExampleConfiguration.contentTrimLength = 4

  val injector = Guice.createInjector(new MockSdgExampleModule(
    mockSdgExampleConfiguration
  ))

  // ------------------------------------------------------------------------
  // On with the testing.
  // ------------------------------------------------------------------------

  "ResourceContentServiceTest" should "load the correct content" in {
    val contentService = injector.instance[ContentService]
    // A single character removed from "Trim!" by the trim length.
    assert(contentService.getContent == "Test")
  }

}
