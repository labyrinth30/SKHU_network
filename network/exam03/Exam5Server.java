package network.exam03;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Exam5Server {
    //클라이언트가 연결되면
    //자신의 학번과 이름을 채운 Student 객체를 클라이언트에 전송한다.
    //연결을 끊는다.
    //다른 클라이언트 연결에 대해서, 위 작업을 계속 반복한다.
    //싱글 쓰레드로 구현한다.

    private static final int PORT = 10015;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                try (Socket socket = serverSocket.accept()) {
                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                    network.exam03.Student st = new Student("201911017", "이윤하");
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
