package services;

import common.Subject;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class SubjectService
{
    private List<Subject> subjects;
    private static SubjectService subjectsService;

    public static synchronized SubjectService getInstance()
    {
        if(subjectsService == null){
            subjectsService = new SubjectService();
        }
        return subjectsService;
    }

    private SubjectService()
    {
        subjects = new ArrayList<>();
        Subject math = new Subject("TPSI","TPSI_TEACHER");
        Subject pe = new Subject("PE","PE_TEACHER");
        Subject english = new Subject("ENGLISH","ENGLISH_TEACHER");

        subjects.add(math);
        subjects.add(pe);
        subjects.add(english);
    }

    public Subject getSubject(long id)
    {
        for(Subject subject: subjects){
            if(subject.getId() == id){
                return subject;
            }
        }
        return null;
    }

    public boolean addSubject(Subject subject)
    {
        return subjects.add(subject);
    }

    public URI getSubjectPath(Subject subject, UriInfo uriInfo)
    {
        UriBuilder ub = uriInfo.getAbsolutePathBuilder();
        return ub.path(subject.getId()+"").build();
    }

    public void updateSubject(Subject currentSubject, Subject newSubject)
    {
        int index = subjects.indexOf(currentSubject);
        int subjectId = currentSubject.getId();
        newSubject.setId(subjectId);
        subjects.set(index,newSubject);
    }

    public void deleteSubject(Subject subject)
    {
        this.subjects.remove(subject);
    }

    public List<Subject> getSubjects()
    {
        return subjects;
    }

}
