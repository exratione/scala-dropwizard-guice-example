package com.exratione.sdgexample

import com.exratione.sdgexample.config._
import com.exratione.sdgexample.guice._
import com.exratione.sdgexample.service._
import com.hubspot.dropwizard.guice.GuiceBundle
import com.yammer.dropwizard.bundles.ScalaBundle
import com.yammer.dropwizard.config.{Bootstrap, Configuration, Environment}
import com.yammer.dropwizard.ScalaService
import net.codingwell.scalaguice.InjectorExtensions._

object SdgExampleScalaService extends ScalaServiceWithGuiceBundle {

  def initialize (bootstrap: Bootstrap[SdgExampleConfiguration]) {
    // Create the bundle for dropwizard-guice integration.
    guiceBundle = GuiceBundle.newBuilder[SdgExampleConfiguration]
      .addModule(new SdgExampleModule)
      // This ensures that Resource implementations in this package are set up
      // automatically.
      .enableAutoConfig(getClass.getPackage.getName)
      // The configuration class will be available via the injector obtained via
      // guiceBundle.getInjector.
      .setConfigClass(classOf[SdgExampleConfiguration])
      .build

    bootstrap.setName("scala-dropwizard-guice-example")
    bootstrap.addBundle(new ScalaBundle)
    bootstrap.addBundle(guiceBundle)
    bootstrap.addCommand(new SdgExampleEnvironmentCommand(this))
  }

  def run (configuration: SdgExampleConfiguration, environment: Environment) {
    // Nothing needs to be done here since the GuiceBundle wires up the
    // Resources automatically.

    // If obtaining instances here to start processes or take other actions
    // then use the injector. E.g.:
    //
    // val injector = guiceBundle.getInjector
    // val contentService = injector.instance[ContentService]
    //
    // Though we are passed the configuration and environment as arguments,
    // dropwizard-guice lets us get these instances from the injector as well.
    // his can be useful elsewhere in an application. E.g.:
    //
    // val config = injector.instance[Configuration]
    // val env = injector.instance[Environment]
  }

}
