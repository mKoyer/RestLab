package resources;

import common.Responses;
import common.Student;
import services.StudentService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("students")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class StudentsResource {

    @Context
    UriInfo uriInfo;

    private StudentService studentService = StudentService.getInstance();

    @GET
    public List<Student> getStudents() {
        return studentService.getStudentList(uriInfo.getQueryParameters());
    }

    @POST
    public Response addStudent(Student student){
        URI uri = studentService.addStudent(student, uriInfo);
        return Responses.created(uri);
    }

    @Path("/{index}")
    public StudentResource getStudent(@PathParam("index") int index) {
        Student student = studentService.getStudent(index);
        if(student == null)
            throw new NotFoundException();
        return new StudentResource();
    }

}

