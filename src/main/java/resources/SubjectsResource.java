package resources;

import common.Responses;
import common.Subject;
import org.bson.types.ObjectId;
import services.SubjectService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("subjects")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class SubjectsResource {
    private SubjectService subjectService = SubjectService.getInstance();

    @GET
    public List<Subject> getSubjects(@Context UriInfo uriInfo) {
        return subjectService.getSubjects(uriInfo.getQueryParameters());
    }

    @POST
    public Response addSubject(Subject subject, @Context UriInfo uriInfo) {
        Subject addedSubject = subjectService.addSubject(subject);
        return Responses.created(subjectService.getSubjectPath(addedSubject,uriInfo));
    }

    @Path("/{id}")
    public SubjectResource getCourseResource(@PathParam("id") ObjectId id){
        return new SubjectResource();
    }

}
