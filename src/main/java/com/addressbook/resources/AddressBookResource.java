package com.addressbook.resources;

import com.addressbook.api.exceptions.NotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;

@Path("/v1.0")
@Produces(MediaType.APPLICATION_JSON)
public class AddressBookResource {
	private static final Logger LOG = LoggerFactory.getLogger(AddressBookResource.class);

	public AddressBookResource() {
	}

	@Path("/ping")
	@GET
	@Timed
	public Response ping() {
		try {
			final Status status = Status.OK;
			return buildResponse(AddressBookResource.class, status, "pong");
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	private Response buildResponse(Class<?> resource, Status status, Object entity) {
		Response.ResponseBuilder builder = Response.created(UriBuilder.fromResource(resource).build());
		builder.status(status).entity(entity);
		Response response = builder.build();
		LOG.debug("Resposne sent: {}", response);
		return response;
	}

	private RuntimeException handleException(Exception ex) {
		Response.Status status;
		StringBuilder sb = new StringBuilder();
        if (ex instanceof NotFoundException) {
            status = Status.NOT_FOUND;
        } else if (ex instanceof IllegalArgumentException) {
            sb.append("Illegal Argument ").append(ex.getMessage());
            status = Status.BAD_REQUEST;
        } else {
            status = Status.INTERNAL_SERVER_ERROR;
            sb.append("Exception ");
            sb.append(ex.getLocalizedMessage());
            sb.append("\nThread ");
            sb.append(Thread.currentThread());
            sb.append('\n');
            for (StackTraceElement element : ex.getStackTrace()) {
                sb.append(element.toString());
                sb.append('\n');
            }
        }
		Response response = Response.status(status).entity(sb.toString()).type("text/plain").build();
		return new WebApplicationException(response);
	}
}
