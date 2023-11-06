package network.chap09;



import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class DaytimeServer5a {
    // 버그가 난 상황
    // 서버 소켓은 하나, 연결된 연결 하나하나가 멀티스레드임.
    public static void main(String[] args) {
        final int PORT = 13, BACKLOG = 200;
        try (ServerSocket serverSocket = new ServerSocket(PORT, BACKLOG)) {
            while (true) {
                // 소켓 객체는 하나만 있는데, 연결이 되면 연결된 소켓 객체가 생김
                try (Socket socket = serverSocket.accept()) {
                    // 연결된 후의 작업만 멀티스레드로 작업
                    Thread thread = new Thread(new DaytimeTask(socket));
                    thread.start();
                    // 여기를 나가면 소켓이 닫힘
                } catch (IOException ex) {
                }
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }


    static class DaytimeTask implements Runnable {
        Socket socket;

        public DaytimeTask(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(100);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                Message msg = new Message("안녕하세요", new Date());
                out.writeObject(msg);
                out.flush();
            } catch (Exception ex) {
            }
        }
    }
}

