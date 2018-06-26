package common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
public class Student
{
    private int index;
    private String name;
    private String surname;
    private Date birthDate;
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
