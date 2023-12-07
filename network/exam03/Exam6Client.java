package network.exam03;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Exam6Client {
    // 서버 주소와 포트 설정
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 10016;

    public static void main(String[] args) {
        // 10개의 스레드를 생성하고 실행
        for (int i = 0; i < 10; i++) {
            Thread clientThread = new Thread(() -> {
                try {
                    // 서버에 소켓 연결
                    Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);

                    // 입력 스트림 생성
                    ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

                    // 서버로부터 Student 객체 읽어오기
                    Student student = (Student) in.readObject();

                    // 학생 정보 출력
                    System.out.println(student.getName() + " " + student.getStudentNo());

                    // 소켓과 스트림 닫기
                    socket.close();
                    in.close();
                } catch (IOException | ClassNotFoundException e) {
                    // 입출력 예외 또는 클래스 찾기 예외 발생 시 예외 내용 출력
                    e.printStackTrace();
                }
            });

            // 스레드 시작
            clientThread.start();
        }
    }
}
