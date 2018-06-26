package common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dao.ObjectIdJaxvAdapter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLinks;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import resources.SubjectResource;
import resources.SubjectsResource;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;

@Entity
@NoArgsConstructor
@XmlRootElement(name = "subject")
@XmlAccessorType(XmlAccessType.FIELD)
public class Subject
{

    @Id
    @XmlTransient
    @XmlJavaTypeAdapter(ObjectIdJaxvAdapter.class)
    private ObjectId id;

    private String name;
    private String teacher;

    @InjectLinks({
            @InjectLink(resource = SubjectsResource.class, rel = "parent", style = InjectLink.Style.ABSOLUTE),
            @InjectLink(resource = SubjectResource.class, rel = "self", style = InjectLink.Style.ABSOLUTE)
    })
    @XmlElement(name = "link")
    @XmlElementWrapper(name = "links")
    @XmlJavaTypeAdapter(Link.JaxbAdapter.class)
    private List<Link> links;

    public Subject(ObjectId id, String teacher, String name) {
        this.id = id;
        this.teacher = teacher;
        this.name = name;
    }

    public List<Link> getLinks()
    {
        return links;
    }

    public void setLinks(List<Link> links)
    {
        this.links = links;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getTeacher()
    {
        return teacher;
    }

    public void setTeacher(String teacher)
    {
        this.teacher = teacher;
    }

    public ObjectId getId()
    {
        return id;
    }

    public void setId(ObjectId id)
    {
        this.id = id;
    }
}
