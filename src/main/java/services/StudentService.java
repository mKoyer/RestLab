package services;

import common.Mark;
import common.MarkValues;
import common.Student;
import common.Subject;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.*;

public class StudentService
{
    private List<Student> studentList = new ArrayList<>();
    private static SubjectService subjectsService = SubjectService.getInstance();
    private static StudentService studentsService;
    private StudentService(){
        for(int i =0;i<10;i++) {
            Student student = new Student("Name"+i, "Surname"+i, new Date());
            studentList.add(student);
            for(int j = 0;j<10;j++){
                int size = subjectsService.getSubjects().size();
                Mark mark = new Mark(MarkValues.getMarkValue((j%3+2) +""),subjectsService.getSubject(j%size));
                addStudentMark(student,mark);
            }
        }
    }

    public Student getStudent(int index){
        for(Student student: studentList){
            if(student.getIndex() == index){
                return student;
            }
        }
        return null;
    }

    public List<Student> getStudentList()
    {
        return studentList;
    }

    public boolean addStudent(Student student){
        return this.studentList.add(student);
    }

    public void updateStudent(Student currentStudent, Student newStudent){
        int index = studentList.indexOf(currentStudent);
        int studentIndex = currentStudent.getIndex();
        newStudent.setIndex(studentIndex);
        getStudentList().set(index,newStudent);
    }

    public static synchronized StudentService getInstance(){
        if(studentsService == null){
            studentsService = new StudentService();
        }
        return studentsService;
    }

    public URI getStudentPath(Student student, UriInfo uriInfo){
        UriBuilder ub = uriInfo.getAbsolutePathBuilder();
        return ub.path(String.valueOf(student.getIndex())).build();
    }

    public Set<Subject> getStudentSubjects(Student student){
        Set<Subject> studentSubjects = new HashSet<Subject>();
        for(Mark mark: student.getMarks()){
            studentSubjects.add(mark.getSubject());
        }
        return studentSubjects;
    }

    public Mark addStudentMark(Student student, Mark mark){
        student.getMarks().add(mark);
        return mark;
    }

    public void deleteStudent(Student student){
        this.studentList.remove(student);
    }

    public Mark getStudentMark(Student student, long id){
        for(Mark m: student.getMarks()){
            if(m.getId() == id){
                return m;
            }
        }
        return null;
    }

    public void deleteStudentMark(Student student, Mark mark){
        student.getMarks().remove(mark);
    }

    public URI getMarkPath(Student student, Mark mark, UriInfo uriInfo) {
        UriBuilder ub = uriInfo.getAbsolutePathBuilder();
        return ub.path(student.getIndex()+"/"+mark.getId()).build();
    }

    public void updateStudentMark(Student student, Mark currentMark, Mark newMark) {
        int index = student.getMarks().indexOf(currentMark);
        int markId = currentMark.getId();
        newMark.setId(markId);
        student.getMarks().set(index,newMark);
    }
}
