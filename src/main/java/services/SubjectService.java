package services;

import common.Subject;
import dao.IDao;
import dao.MongoDao;
import org.bson.types.ObjectId;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class SubjectService
{
    private IDao dao = MongoDao.getInstance();
    private static SubjectService subjectService;
    private SubjectService(){ }

    public Subject getSubject(ObjectId id){
        return dao.getSubject(id);
    }

    public List<Subject> getSubjects(MultivaluedMap<String, String> params){
        return dao.getSubjects(params);
    }

    public Subject addSubject(Subject subject){
        return dao.saveSubject(subject);
    }

    public URI getSubjectPath(Subject subject, UriInfo uriInfo){
        UriBuilder ub = uriInfo.getAbsolutePathBuilder();
        return ub.path(subject.getId()+"").build();
    }

    public void updateSubject(ObjectId id, Subject newSubject){
        newSubject.setId(id);
        dao.updateSubject(newSubject);
    }

    public void deleteSubject(Subject subject){
        dao.delete(subject);
    }

    public static synchronized SubjectService getInstance(){
        if(subjectService == null){
            subjectService = new SubjectService();
        }
        return subjectService;
    }

}
