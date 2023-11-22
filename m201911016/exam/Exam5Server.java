package network.chap01;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exam5Server {

    public static void main(String[] args) {
        final int PORT = 12349;
        ExecutorService executorService = Executors.newFixedThreadPool(200);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    executorService.submit(new StudentTask(socket));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }

    static class StudentTask implements Runnable {
        Socket socket;

        public StudentTask(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                // 학번과 이름 전송
                writer.write("201911016 이윤하");
                writer.newLine();
                writer.flush();

                // 연결 종료
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}