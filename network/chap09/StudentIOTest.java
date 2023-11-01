package network.chap09;

import java.io.*;
import java.nio.Buffer;

public class StudentIOTest {
    public static void main(String[] args) {
        final String fileName = "student.dat";
        try (
                var out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
        ) {
            Student student = new Student("201911016", "이윤하");
            for (int i = 0; i < 3; i++) {
                out.writeObject(student);
            }
        } catch (Exception e) {
        }
        try (
                var in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)));
        ) {
            while(true) {
                Student student = (Student) in.readObject();
                System.out.println(student.getStudentId() + " " + student.getName());
            }

        } catch (Exception e) {
        }
    }

}



