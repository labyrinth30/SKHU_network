package network.exam03;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Exam3Server {
    //Port 번호: 10013
    //클라이언트가 연결되면,
    //   자신의 학번과 이름 문자열을 클라이언트에게 전송하고,
    //   연결을 끊는다.
    //여러 클라이언트 연결에 대해서, 위 작업을 계속 반복한다.
    //싱글 쓰레드로 구현한다.
    public static void main(String[] args) {
        int portNumber = 10013;

        try {
            // 서버 소켓 생성
            ServerSocket serverSocket = new ServerSocket(portNumber);

            System.out.println("서버가 " + portNumber + " 포트에서 대기 중입니다...");

            while (true) {
                // 클라이언트 연결 대기
                Socket clientSocket = serverSocket.accept();

                // 클라이언트에 학번과 이름 전송
                String studentInfo = "201911016 이윤하";
                sendStudentInfo(clientSocket.getOutputStream(), studentInfo);

                // 클라이언트와의 연결 종료
                clientSocket.close();
            }
        } catch (IOException e) {
            System.err.println("서버에서 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void sendStudentInfo(OutputStream outputStream, String studentInfo) throws IOException {
        // 학번과 이름을 클라이언트에게 전송
        outputStream.write(studentInfo.getBytes());
        outputStream.flush();
    }
}



