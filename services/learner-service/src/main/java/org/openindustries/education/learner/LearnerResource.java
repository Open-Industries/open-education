package org.openindustries.education.learner;

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

@Path("/api/v1/learners")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Learners", description = "Learner registry; the institutional identifier is masked at enrolment.")
public class LearnerResource {

    @Inject
    LearnerService service;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Create a learner")
    public Response create(@Valid CreateLearnerRequest request) {
        return Response.status(Response.Status.CREATED).entity(service.create(request)).build();
    }

    @GET
    @Operation(summary = "List all")
    public List<Learner> list() {
        return service.list();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get one by id")
    public Learner get(@PathParam("id") UUID id) {
        return service.get(id);
    }

    @POST
    @Path("/{id}/suspend")
    @Operation(summary = "Suspend this learner (lifecycle transition, no body)")
    public Learner suspend(@PathParam("id") UUID id) {
        return service.suspend(id);
    }

    @POST
    @Path("/{id}/graduate")
    @Operation(summary = "Graduate this learner (lifecycle transition, no body)")
    public Learner graduate(@PathParam("id") UUID id) {
        return service.graduate(id);
    }
}
