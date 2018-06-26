package common;

public class Utils
{
    private static int studentIndex = 0;
    private static int markId = 0;
    private static int subjectId = 0;
    public static synchronized int getStudentIndex(){
        return studentIndex++;
    }
    public static synchronized int getMarkId(){
        return markId++;
    }
    public static synchronized int getSubjectId(){
        return subjectId++;
    }
}
