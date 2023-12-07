package network.exam03;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Exam4Client {
    public static void main(String[] args) throws IOException{
        String serverAddress = "localhost";
        int portNumber = 10014;

        try {
            // 서버에 연결
            Socket socket = new Socket(serverAddress, portNumber);

            // 서버로 학번과 이름 문자열 전송
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println("201911016 이윤하");
            // sendStudentInfo(socket.getOutputStream(), "201911016 이윤하");

            // 소켓 닫기
            socket.close();
        } catch (IOException e) {
            System.err.println("클라이언트에서 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }

//    private static void sendStudentInfo(OutputStream outputStream, String studentInfo) throws IOException {
//        // 서버로 학번과 이름 문자열 전송
//        PrintWriter writer = new PrintWriter(outputStream, true);
//        writer.println(studentInfo);
//    }
}
