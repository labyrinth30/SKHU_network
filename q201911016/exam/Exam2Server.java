package q201911016.exam;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

//클라이언트와 연결되면, 클라이언트가 전송한 Student 객체의 학번과 이름을 화면에 출력한다.
public class Exam2Server {
    private static final int PORT = 10015;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                try (Socket socket = serverSocket.accept()) {
                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                    Student st = new Student("201911016", "이윤하");
                    out.writeObject(st);
                    out.flush();
                } catch (IOException ex) {
                }
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}