package network.chap09;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class DaytimeServer5b {

    public static void main(String[] args) {
        final int PORT = 13, BACKLOG = 200;
        try (ServerSocket serverSocket = new ServerSocket(PORT, BACKLOG)) {
            while (true) {
                try {
                    // 디버깅함.
                    Socket socket = serverSocket.accept();
                    Thread thread = new Thread(new DaytimeTask(socket));
                    thread.start();
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
                // 여기서 연결을 끊음
                socket.close();
            } catch (Exception ex) {
            }
        }
    }
}

