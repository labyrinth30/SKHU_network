package network.chap01;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Exam4Server {

    public static void main(String[] args) {
        final int PORT = 12348;

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            Socket socket = serverSocket.accept();

            // 학번과 이름 5번 전송
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            for (int i = 0; i < 5; i++) {
                writer.println("201911016, 이윤하");
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
