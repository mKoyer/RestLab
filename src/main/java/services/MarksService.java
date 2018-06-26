package services;

import common.Mark;
import common.Student;
import common.Subject;
import dao.IDao;
import dao.MongoDao;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class MarksService
{
    private IDao dao = MongoDao.getInstance();
    private static MarksService marksService;
    private MarksService() {}

    public List<Mark> getMarks(int index, MultivaluedMap<String, String> params) {
        Student student = dao.getStudent(index);
        List<Mark> marks = student.getMarks();
        if(params.containsKey("subject")){
            marks.removeIf(mark -> !mark.getSubject().getId().toString().contains(params.getFirst("subject")));
        }
        if(params.containsKey("mark")) {
            float mark = Float.parseFloat(params.getFirst("mark"));
            String op = "";
            if(params.containsKey("op")) {
                op = params.getFirst("op");
            }
            switch(op) {
                case "gt":
                    marks.removeIf(m -> Float.parseFloat(m.getValue()) <= mark);
                    break;
                case "":
                    marks.removeIf(m -> Float.parseFloat(m.getValue()) != mark);
                    break;
                case "lt":
                    marks.removeIf(m -> Float.parseFloat(m.getValue()) >= mark);
                    break;
                default:
                    throw new BadRequestException();
            }
        }
        return marks;
    }

    public Mark getMark(int index, int id) {
        return dao.getStudent(index)
                .getMarks()
                .stream()
                .filter(mark -> mark.getId() == id)
                .findFirst().orElse(null);
    }

    public void updateMark(int index, Mark newMark) {
        Student student = dao.getStudent(index);
        Mark currentMark = null;
        for(Mark mark :student.getMarks()){
            if(mark.getId() == newMark.getId()){
                currentMark = mark;
            }
        }
        int markIndex = student.getMarks().indexOf(currentMark);
        student.getMarks().set(markIndex,newMark);
        dao.updateStudent(student);
    }

    public Mark addMark(int index, Mark mark) {
        Student student = dao.getStudent(index);
        mark.setId(dao.getNextMarkId());
        student.getMarks().add(mark);
        dao.updateStudent(student);
        return mark;
    }

    public void deleteMark(int index, int id) {
        Student student = dao.getStudent(index);
        Mark markToDelete = null;
        for(Mark mark: student.getMarks()){
            if(mark.getId() == id){
                markToDelete = mark;
            }
        }
        student.getMarks().remove(markToDelete);
        dao.updateStudent(student);
    }

    public URI getMarkPath(Mark mark, UriInfo uriInfo) {
        UriBuilder ub = uriInfo.getAbsolutePathBuilder();
        return ub.path("/"+mark.getId()).build();
    }

    public static synchronized MarksService getInstance() {
        if (marksService == null) {
            marksService = new MarksService();
        }
        return marksService;
    }
}
