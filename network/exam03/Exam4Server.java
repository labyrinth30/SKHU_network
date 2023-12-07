package network.exam03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Exam4Server {
    //Port 번호: 10014
    //클라이언트가 연결되면,
    //   클라이언트가 전송한 문자열을 읽어서, 화면에 출력한다.
    //   연결을 끊는다.
    //여러 클라이언트 연결에 대해서, 위 작업을 계속 반복한다.
    //싱글 쓰레드로 구현한다.
    public static void main(String[] args) throws IOException {
        int portNumber = 10014;

        try {
            // 서버 소켓 생성
            ServerSocket serverSocket = new ServerSocket(portNumber);

            System.out.println("서버가 " + portNumber + " 포트에서 대기 중입니다...");

            while (true) {
                // 클라이언트 연결 대기
                Socket clientSocket = serverSocket.accept();

                // 클라이언트로부터 문자열 받고 출력
                // String receivedString = receiveString(clientSocket.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String receivedString = reader.readLine();
                System.out.println("클라이언트에서 보낸 메시지: " + receivedString);

                // 클라이언트와의 연결 종료
                System.out.println("클라이언트와 연결을 종료합니다.");
                clientSocket.close();
            }
        } catch (IOException e) {
            System.err.println("서버에서 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }

//    private static String receiveString(InputStream inputStream) throws IOException {
//        // 클라이언트에서 문자열 수신
//        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//        return reader.readLine();
//    }
}



