package services;
import common.Mark;
import common.Student;
import common.Subject;
import dao.IDao;
import dao.MongoDao;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StudentService {
    private IDao dao = MongoDao.getInstance();
    private static StudentService studentService;
    private StudentService(){ }

    public Student getStudent(int index){
        return dao.getStudent(index);
    }

    public List<Student> getStudentList(MultivaluedMap<String, String> params){
        return dao.getStudents(params);
    }

    public URI addStudent(Student student, UriInfo uriInfo){
        dao.saveStudent(student);
        return getStudentPath(student, uriInfo);
    }

    public void updateStudent(int index, Student newStudent){
        newStudent.setIndex(index);
        dao.updateStudent(newStudent);
    }

    public URI getStudentPath(Student student, UriInfo uriInfo){
        UriBuilder ub = uriInfo.getAbsolutePathBuilder();
        return ub.path(student.getIndex() + "").build();
    }

    public Set<Subject> getStudentSubjects(Student student){
        Set<Subject> studentSubjects = new HashSet<Subject>();
        for(Mark mark: student.getMarks()){
            studentSubjects.add(mark.getSubject());
        }
        return studentSubjects;
    }

    public void deleteStudent(Student student){
        dao.delete(student);
    }

    public static synchronized StudentService getInstance(){
        if(studentService == null){
            studentService = new StudentService();
        }
        return studentService;
    }
}

