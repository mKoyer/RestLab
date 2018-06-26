package services;

import common.Mark;
import common.Student;
import common.Subject;

import java.util.ArrayList;
import java.util.List;

public class MarksService
{
    private static MarksService marksService;
    private static SubjectService subjectsService = SubjectService.getInstance();
    private static StudentService studentsService = StudentService.getInstance();

    public List<Mark> getAllMarks(){
        List<Mark> marks = new ArrayList<>();
        for(Student student: studentsService.getStudentList()){
            marks.addAll(student.getMarks());
        }
        return marks;
    }

    public void updateMark(Mark currentMark, Mark newMark) {
        String value = newMark.getValue();
        int subjectId = newMark.getSubject().getId();
        if(!value.equals(""))
            currentMark.setValue(value);
        if(subjectId != -1) {
            Subject newSubject = subjectsService.getSubject(subjectId);
            currentMark.setSubject(newSubject);
        }
    }

    public static synchronized MarksService getInstance(){
        if(marksService == null){
            marksService = new MarksService();
        }
        return marksService;
    }

    public Mark getMark(int id){
        Mark m = null;
        for(Mark mark: marksService.getAllMarks()){
            if(mark.getId() == id)
                m = mark;
        }
        return m;
    }

    public void deleteMark(Mark mark) {
        getAllMarks().remove(mark);
    }
}
