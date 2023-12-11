// Exam1Server.java
package q201911016.exam;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

// 클라이언트가 연결되면 클라이언트에서 보낸 Student 객체의 학번과 이름을 화면에 표시
public class Exam1Server {
    private static final int PORT = 10015;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                try (Socket socket = serverSocket.accept()) {
                    // 클라이언트에서 받은 Student 객체 읽기
                    ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                    Student student = (Student) in.readObject();

                    // 받은 정보를 화면에 표시
                    System.out.println(student.getStudentNo() + " " + student.getName());
                } catch (Exception e) {
                    e.printStackTrace(); 
                }
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
