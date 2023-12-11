// Exam3Client.java
package q201911016.exam;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

// 서버에 연결하여 Student 객체에 학번과 이름을 채우고, 서버로 전송한 뒤 연결 해제
public class Exam3Client {
    public static void main(String[] args) throws Exception {
        final String HOST = "localhost";
        final int PORT = 10015;

        try (Socket socket = new Socket(HOST, PORT)) {
            // 클라이언트에서 보낼 Student 객체 생성
            Student st = new Student("201911016", "이윤하");

            // 서버로 Student 객체 전송
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(st);
            out.flush();
        }
    }
}
