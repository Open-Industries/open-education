package org.openindustries.education.course;

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

@Path("/api/v1/courses")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Courses", description = "Course catalogue with credit values and modalities.")
public class CourseResource {

    @Inject
    CourseService service;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Create a course")
    public Response create(@Valid CreateCourseRequest request) {
        return Response.status(Response.Status.CREATED).entity(service.create(request)).build();
    }

    @GET
    @Operation(summary = "List all")
    public List<Course> list() {
        return service.list();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get one by id")
    public Course get(@PathParam("id") UUID id) {
        return service.get(id);
    }

    @POST
    @Path("/{id}/publish")
    @Operation(summary = "Publish (lifecycle transition, no body)")
    public Course publish(@PathParam("id") UUID id) {
        return service.publish(id);
    }
}
