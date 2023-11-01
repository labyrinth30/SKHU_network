package network.chap09;

import java.io.ObjectInputStream;
import java.net.Socket;

public class StudentClient1 {

    public static void main(String[] args) throws Exception {
        final String HOST = "localhost";
        try (Socket socket = new Socket(HOST, 10003)) {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            Student student = (Student)in.readObject();
            System.out.printf("%s %s\n", student.getStudentId(), student.getName());
        }
    }

}

