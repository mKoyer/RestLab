package common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import endpoints.StudentsEndpoint;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLinks;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
public class Student
{

    @InjectLinks({
            @InjectLink(resource = StudentsEndpoint.class, rel = "parent", method = "getStudents",style = InjectLink.Style.ABSOLUTE),
            @InjectLink(resource = StudentsEndpoint.class, rel = "self", method = "getStudent", style = InjectLink.Style.ABSOLUTE)
    })
    @XmlElement(name = "links")
    @XmlElementWrapper(name = "links")
    @XmlJavaTypeAdapter(Link.JaxbAdapter.class)
    List<Link> links;

    public List<Link> getLinks()
    {
        return links;
    }

    public void setLinks(List<Link> links)
    {
        this.links = links;
    }

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
        this.index = Utils.getStudentIndex();
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
