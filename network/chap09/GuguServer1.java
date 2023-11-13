package network.chap09;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GuguServer1 {

    public static void main(String[] args) {
        final int PORT = 9090;
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            ExecutorService executor = Executors.newFixedThreadPool(200);
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    executor.submit(new GuguTask(socket));
                } catch (IOException ex) {
                }
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    static class GuguTask implements Runnable {
        Socket socket;

        public GuguTask(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            //클라이언트가 전송한 정수 두 개를 읽어서, 두 수를 곱해서 클라이언트에 전송한다.
            //클라이언트가 연결을 끊을 때까지 while 문을 계속 반복한다.
            //클라이언트가 연결을 끊으면, IOException 에러가 발생하고, run 메소드에서 리턴하게 된다.
            try (Socket autoClose = socket) {
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                while (true) {
                    int a = in.readInt();
                    int b = in.readInt();
                    out.writeInt(a * b);
                    out.flush();
                }
            } catch (IOException ex) {
            }
        }
    }
}

