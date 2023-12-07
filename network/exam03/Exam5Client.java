package network.exam03;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Exam5Client {
    // 서버가 전송한 Student 객체의 학번과 이름 속성값을 화면에 출력하고 종료한다.

    public static void main(String[] args) throws Exception {
        final String HOST = "localhost";
        final int PORT = 10015;
        try (Socket socket = new Socket(HOST, PORT)) {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            Student st = (Student) in.readObject();
            System.out.println("서버로부터 전달받은 " + st.getStudentNo()+" "+st.getName());
        }
    }

}
