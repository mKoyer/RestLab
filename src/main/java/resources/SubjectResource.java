package resources;

import common.Responses;
import common.Subject;
import org.bson.types.ObjectId;
import services.SubjectService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class SubjectResource {
    private SubjectService subjectService = SubjectService.getInstance();

    @GET
    public Response getSubject(@PathParam("id") ObjectId id) {
        Subject subject = subjectService.getSubject(id);
        return subject == null ? Responses.notFound() : Responses.ok(subject);
    }

    @PUT
    public Response updateSubject(@PathParam("id") ObjectId id, Subject subject) {
        subjectService.updateSubject(id, subject);
        return Responses.ok();
    }

    @DELETE
    public Response deleteSubject(@PathParam("id") ObjectId id){
        Subject subject = subjectService.getSubject(id);
        if(subject == null)
            return Responses.notFound();
        subjectService.deleteSubject(subject);
        return Responses.noContent();
    }


}
