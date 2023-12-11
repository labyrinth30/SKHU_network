// Exam3Server.java
package q201911016.exam;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exam3Server {
    private static final int PORT = 10015;
    private static final ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    executorService.submit(new ClientHandler(socket));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            executorService.shutdown();
        }
    }

    private static class ClientHandler implements Runnable {
        private final Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
                // 클라이언트에서 받은 Student 객체 읽기
                Student st = (Student) in.readObject();

                // 받은 정보를 서버 콘솔에 출력
                System.out.println(st.getStudentNo() + " " + st.getName());
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
