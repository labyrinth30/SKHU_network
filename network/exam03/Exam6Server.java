package network.exam03;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exam6Server {

    private static final int PORT = 10016;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("서버가 " + PORT + " 포트에서 대기 중입니다...");

            while (true) {
                try {
                    // 클라이언트 연결 대기
                    Socket socket = serverSocket.accept();

                    // 각 클라이언트에 대한 작업을 스레드 풀에 할당
                    executorService.submit(() -> handleClient(socket));

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (IOException ex) {
            System.err.println("서버에서 오류 발생: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            // 스레드 풀 종료
            executorService.shutdown();
        }
    }

    private static void handleClient(Socket socket) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            Student student = new Student("201911016", "이윤하");
            out.writeObject(student);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 클라이언트와의 연결 종료
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
