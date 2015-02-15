package com.exratione.sdgexample.resource

import com.exratione.sdgexample.service._
import com.google.inject.Inject
import javax.ws.rs._
import javax.ws.rs.core.MediaType

/**
 * This resource will be added to the Dropwizard environment automatically by
 * the GuiceBundle created in SdgExampleScalaService.
 *
 * Since this is marked as @Inject() the arguments will be injected.
 */
@Path("/content")
@Produces(Array(MediaType.TEXT_PLAIN))
class ContentDisplayResource @Inject() (
  contentService: ContentService
) {

  @GET
  def getContent(): String = {
    contentService.getContent
  }

}
