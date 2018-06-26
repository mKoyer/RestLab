package common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Date;

@XmlRootElement(name="mark")
@XmlAccessorType(XmlAccessType.FIELD)
public class Mark
{
    @JsonIgnore
    @XmlTransient
    private int id;
    private String value;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;
    private Subject subject;

    public Mark(String value, Subject subject)
    {
        this.id = Utils.getMarkId();
        this.value = value;
        this.date = new Date();
        this.subject = subject;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public Subject getSubject()
    {
        return subject;
    }

    public void setSubject(Subject subject)
    {
        this.subject = subject;
    }
}
