package network.chap09;

import java.io.ObjectInputStream;
import java.net.Socket;

public class DaytimeClient5 {
    static final String HOST = "localhost";
    static final int PORT = 13;

    // 멀티스레드 구현 방법1
    static class GetMessageTask implements Runnable {
        @Override
        public void run() {
            // 여기서 소켓 연결을 끊음
            try (Socket socket = new Socket(HOST, PORT)) {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Message msg = (Message) in.readObject();
                System.out.printf("%s %s\n", msg.getValue(), msg.getDate());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 200; ++i)
            // 새 실행흐름이 만들어짐, 200개의 실행흐름이 멀티스레드로 서버에 동시에 연결 시도
            // 서버랑 연결하는 시간 떄문에 에러가 먼저 나오고 그 다음에 시간이 출력됨
            new Thread(new GetMessageTask()).start();
    }

}

