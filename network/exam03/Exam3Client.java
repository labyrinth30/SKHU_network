package network.exam03;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Exam3Client {
    // 서버가 전송한 문자열을 화면에 출력하고 종료한다.
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int portNumber = 10013;

        try {
            // 서버에 연결
            Socket socket = new Socket(serverAddress, portNumber);

            // 서버에서 전송한 학번과 이름 수신
            String receivedInfo = receiveStudentInfo(socket.getInputStream());

            // 수신한 정보 출력
            System.out.println("서버에서 받은 정보: " + receivedInfo);

            // 소켓 닫기
            socket.close();
        } catch (IOException e) {
            System.err.println("클라이언트에서 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static String receiveStudentInfo(InputStream inputStream) throws IOException {
        // 서버에서 전송한 학번과 이름 수신
        byte[] buffer = new byte[1024];
        int bytesRead = inputStream.read(buffer);
        return new String(buffer, 0, bytesRead);
    }
}
