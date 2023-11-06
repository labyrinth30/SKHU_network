package network.chap09;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class DaytimeServer4 {

    public static void main(String[] args) {
        final int PORT = 13;
        // 서버 소켓 만들어서 리스닝포트에 바인딩
        // 포트번호를 서버소켓이 차지한다 == 바인딩, 오기까지 기다린다 == 리스닝
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                // 연결이 될 때까지 리턴을 안 함
                try (Socket socket = serverSocket.accept()) {
                    // 문자열 보내려면 OutputStreamWriter로 감싸서 보내면 됨
                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                    // 연결이 되면 accept메소드가 리턴함
                    // 연결 되었다는 건 소켓이 만들어졌다는 것
                    // 보통 데이터를 텍스트로(Json format 텍스트) 보냄
                    Message msg = new Message("안녕하세요", new Date());
                    // 출력하는 것
                    out.writeObject(msg);
                    out.flush();
                } catch (Exception ex) {
                }
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}

