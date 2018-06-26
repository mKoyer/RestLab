package common;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "subject")
@XmlAccessorType(XmlAccessType.FIELD)
public class Subject
{
    @JsonIgnore
    @XmlTransient
    private int id;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    private String name;
    private String teacher;

    public Subject()
    {
        this.id = Utils.getSubjectId();
    }

    public Subject(String name, String teacher)
    {
        this();
        this.name = name;
        this.teacher = teacher;
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
}
