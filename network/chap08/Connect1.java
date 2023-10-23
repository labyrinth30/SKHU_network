package network.chap08;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class Connect1 {
//연결상태 확인: (socket.isConneced() && !socket.isClosed())
    public static void main(String[] args) throws IOException {
        String host = "www.skhu.ac.kr";
        int port = 80;

        try (Socket socket = new Socket()) {
            System.out.printf("before connect: connected %s, closed %s\n",
                    socket.isConnected(), socket.isClosed());

            SocketAddress address = new InetSocketAddress(host, port);
            socket.connect(address, 1000);
            System.out.printf("after connect: connected %s, closed %s\n",
                    socket.isConnected(), socket.isClosed());

            socket.close();
            System.out.printf("after close: connected %s, closed %s\n",
                    socket.isConnected(), socket.isClosed());
        }

    }

}

