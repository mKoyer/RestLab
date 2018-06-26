package resources;

import common.Responses;
import common.Student;
import common.Subject;
import services.StudentService;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.Set;

@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class StudentResource {
    private StudentService studentService = StudentService.getInstance();

    @Context
    UriInfo uriInfo;

    @GET
    public Student getStudent(@PathParam("index") int index) {
        return studentService.getStudent(index);
    }

    @PUT
    public Response updateStudent(@PathParam("index") int index, Student student) {
        studentService.updateStudent(index, student);
        return Responses.ok();
    }

    @DELETE
    public Response deleteSubject(@PathParam("index") int index) {
        Student student = studentService.getStudent(index);
        if (student == null)
            return Responses.notFound();
        studentService.deleteStudent(student);
        return Responses.noContent();
    }

    @Path("/marks")
    public MarksResource getStudentMarks(@PathParam("index") int index) {
        return new MarksResource(index);
    }

    @Path("/subjects")
    public Response getStudentSubjects(@PathParam("index") int index) {
        Student student = studentService.getStudent(index);
        if (student == null)
            return Responses.notFound();
        return Responses.ok(new GenericEntity<Set<Subject>>(studentService.getStudentSubjects(student)) {
        });
    }


}

