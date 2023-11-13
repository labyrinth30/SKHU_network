package network.chap09;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EchoServer2 {

    public static void main(String[] args) {
        final int PORT = 9090, BACKLOG = 400;
        // 무한 루프라 강제종료 전까지는 try문을 안 빠져나옴
        // 중괄호를 빠져나올 때 close됨
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            // 스레드는 최대 200개
            ExecutorService executor = Executors.newFixedThreadPool(200);
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    executor.submit(new EchoTask(socket));
                } catch (IOException ex) {
                }
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    static class EchoTask implements Runnable {
        Socket socket;

        public EchoTask(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            // 소켓객체는 main에서 만들어졌으므로
            // close하는 걸 여기서 함
            try (Socket autoClose = socket) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(autoClose.getInputStream(), "UTF-8"));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(autoClose.getOutputStream(), "UTF-8"));
                // 서버는 무한히 반복하다가 클라가 연결 끊음
                while(true) {
                    String s = reader.readLine();
                    writer.write(s.toUpperCase());
                    writer.write("\r\n");
                    // flush해야 전송이 됨
                    writer.flush();
                }
            } catch (Exception ex) {
            }
        }
    }
}

