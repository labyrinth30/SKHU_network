package network.exam03;

import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    private String studentNo;
    private String name;

    public Student(String studentNo, String name) {
        this.studentNo = studentNo;
        this.name = name;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public String getName() {
        return name;
    }
}
