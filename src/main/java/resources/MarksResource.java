package resources;

import common.Mark;
import common.Responses;
import services.MarksService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class MarksResource {
    private MarksService marksService = MarksService.getInstance();
    private int index;

    MarksResource(int index) {
        this.index = index;
    }

    @GET
    public List<Mark> getStudentMarks(@Context UriInfo uriInfo) {
        return marksService.getMarks(index, uriInfo.getQueryParameters());
    }

    @POST
    public Response addMark(Mark mark, @Context UriInfo uriInfo) {
        marksService.addMark(index, mark);
        return Responses.created(marksService.getMarkPath(mark, uriInfo));
    }

    @Path("/{id}")
    public MarkResource getMarkResource(@PathParam("id") int id) {
        return new MarkResource(index);
    }
}