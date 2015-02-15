package com.exratione.sdgexample

import com.exratione.sdgexample.config._
import com.exratione.sdgexample.guice._
import com.exratione.sdgexample.service._
import com.hubspot.dropwizard.guice.GuiceBundle
import com.yammer.dropwizard.cli.EnvironmentCommand
import com.yammer.dropwizard.config.Environment
import com.yammer.dropwizard.ScalaService
import net.codingwell.scalaguice.InjectorExtensions._
import net.sourceforge.argparse4j.inf.Namespace
import org.slf4j.LoggerFactory

class SdgExampleEnvironmentCommand (
  application: ScalaService[SdgExampleConfiguration]
) extends EnvironmentCommand[SdgExampleConfiguration] (
  application,
  "sdg",
  "An example to show how to use dropwizard-guice."
) {

  private val logger = LoggerFactory.getLogger(getClass)
  private val guiceBundle = application
    .asInstanceOf[ScalaServiceWithGuiceBundle]
    .guiceBundle

  override def run (
    environment: Environment,
    namespace: Namespace,
    configuration: SdgExampleConfiguration
  ): Unit = {

    val injector = guiceBundle.getInjector
    val contentService = injector.instance[ContentService]

    // Though we are passed the configuration and environment, dropwizard-guice
    // lets us get these instances from the injector as well. This can be
    // useful elsewhere in an application. E.g.:
    //
    // val config = injector.instance[Configuration]
    // val env = injector.instance[Environment]

    // Print out the content - that is the sole purpose of this example command.
    logger.info(s"""Content: ${contentService.getContent}""")
  }

}
