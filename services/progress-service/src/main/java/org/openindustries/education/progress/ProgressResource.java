package org.openindustries.education.progress;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;
import java.util.UUID;

@Path("/api/v1/progress")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Credit Progress", description = "Remaining-credits accounts per learner and the credit awards against them.")
public class ProgressResource {

    @Inject
    ProgressService service;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Open a creditprogress")
    public Response open(@Valid OpenCreditProgressRequest request) {
        return Response.status(Response.Status.CREATED).entity(service.open(request)).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get one by id")
    public CreditProgress get(@PathParam("id") UUID id) {
        return service.get(id);
    }

    @POST
    @Path("/{id}/awards")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Draw from the account (rejects if it would overdraw, 422)")
    public CreditAward consume(@PathParam("id") UUID id, @Valid CreateCreditAwardRequest request) {
        return service.consume(id, request);
    }

    @GET
    @Path("/{id}/awards")
    @Operation(summary = "List consumption records of an account")
    public List<CreditAward> records(@PathParam("id") UUID id) {
        return service.recordsOf(id);
    }
}
