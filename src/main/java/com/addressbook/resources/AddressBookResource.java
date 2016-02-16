package com.addressbook.resources;

import com.addressbook.api.exceptions.*;

import com.yammer.dropwizard.logging.Log;
import com.yammer.metrics.annotation.Timed;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;

@Path("/v1.0")
@Produces(MediaType.APPLICATION_JSON)
public class AddressBookResource {
    private static final Log LOG = Log.forClass(AddressBookResource.class);
    protected static JsonNodeFactory fact = JsonNodeFactory.instance;

    public AddressBookResource() {
    }

    @Path("/ping")
    @GET
    @Timed
    public Response ping() {
        try {
            final Status status = Status.OK;
            ObjectNode result = fact.objectNode();
            result.put("status", status.getStatusCode());
            result.put("message", "pong");
            return buildResponse(AddressBookResource.class, status, result);
        } catch (Exception e) {
            throw handleException(e);
        }
    }

    private RuntimeException handleException(Exception ex) {
        Response.Status status;
        StringBuilder sb = new StringBuilder();
        if (ex instanceof NotFoundException) {
            sb.append("Topic ").append(ex.getMessage())
                    .append(" does not exist.");
            status = Status.NOT_FOUND;
        } else if (ex instanceof NotFoundException) {
            sb.append("Message ").append(ex.getMessage())
                    .append(" does not exist.");
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
        Response response = Response.status(status).entity(sb.toString())
                .type("text/plain").build();
        return new WebApplicationException(response);
    }

    private Response buildResponse(Class<?> resource, Status status,
            Object entity) {
        Response.ResponseBuilder builder = Response.created(UriBuilder
                .fromResource(resource).build());
        builder.status(status).entity(entity);
        Response response = builder.build();
        LOG.debug("Resposne sent: {}", response);
        return response;
    }
}
