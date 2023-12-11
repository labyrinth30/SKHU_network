package q201911016.exam;

import java.io.ObjectInputStream;
import java.net.Socket;

//서버에 연결하고, Student 객체에 자신의 학번과 이름 채워서 서버에 전송하고 연결을 끊는다.
public class Exam2Client {
    public static void main(String[] args) throws Exception {
        final String HOST = "localhost";
        final int PORT = 10015;
        try (Socket socket = new Socket(HOST, PORT)) {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            Student st = (Student) in.readObject();
            System.out.println(st.getStudentNo()+" "+st.getName());
        }
    }

}