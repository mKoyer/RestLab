package common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLinks;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.IndexOptions;
import org.mongodb.morphia.annotations.Indexed;
import resources.MarksResource;
import resources.StudentsResource;
import resources.SubjectsResource;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Entity
@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
public class Student
{

    @InjectLinks({
            @InjectLink(resource = StudentsResource.class, rel = "self",  style = InjectLink.Style.ABSOLUTE),
            @InjectLink(resource = SubjectsResource.class, rel = "subjects", style = InjectLink.Style.ABSOLUTE),
            @InjectLink(resource = MarksResource.class, rel = "marks", style = InjectLink.Style.ABSOLUTE),
    })
    @XmlElement(name = "links")
    @XmlElementWrapper(name = "links")
    @XmlJavaTypeAdapter(Link.JaxbAdapter.class)
    List<Link> links;

    @Id
    @XmlTransient
    private ObjectId id;


    public ObjectId getId()
    {
        return id;
    }

    public void setId(ObjectId id)
    {
        this.id = id;
    }

    public List<Link> getLinks()
    {
        return links;
    }

    public void setLinks(List<Link> links)
    {
        this.links = links;
    }
    @Indexed(options = @IndexOptions(unique = true))
    private int index;
    private String name;
    private String surname;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthDate;
    @JsonIgnore
    @XmlTransient
    private List<Mark> marks;

    public Student()
    {
        this.marks = new ArrayList<>();
    }

    public Student(String name, String surname, Date birthDate)
    {
        this();
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
    }

    public int getIndex()
    {
        return index;
    }

    public void setIndex(int index)
    {
        this.index = index;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public Date getBirthDate()
    {
        return birthDate;
    }

    public void setBirthDate(Date birthDate)
    {
        this.birthDate = birthDate;
    }

    public List<Mark> getMarks()
    {
        return marks;
    }

    public void setMarks(List<Mark> marks)
    {
        this.marks = marks;
    }

    public void addMark(Mark mark)
    {
        this.marks.add(mark);
    }

    public void remove(Mark mark)
    {
        this.marks.remove(mark);
    }
}
