package dao;

import common.Student;
import common.Subject;
import org.bson.types.ObjectId;

import javax.ws.rs.core.MultivaluedMap;
import java.util.List;

public interface IDao
{
    List<Student> getStudents(MultivaluedMap<String,String> params);
    Student getStudent(int index);
    Student saveStudent(Student student);
    void updateStudent(Student student);

    List<Subject> getSubjects(MultivaluedMap<String,String> params);
    Subject getSubject(ObjectId id);
    Subject saveSubject(Subject subject);
    void updateSubject(Subject subject);

    void delete(Object object);
    int getNextStudentIndex();
    int getNextMarkId();
}
