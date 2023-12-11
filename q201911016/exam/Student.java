package q201911016.exam;

import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    private String studentNo;
    private String name;

    public Student(String studentNo, String name) {
        this.studentNo = studentNo;
        this.name = name;
    }
    public Student(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }
}
