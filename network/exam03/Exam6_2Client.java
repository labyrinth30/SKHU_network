package network.exam03;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Exam6_2Client {
    // 서버 주소와 포트 설정
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 10016;

    public static void main(String[] args) {
        for(int i = 0; i < 10; i++){
            new Thread(new StudentTask()).start();
        }
    }

    public static class StudentTask implements Runnable{
        @Override
        public void run() {
            // 서버에 소켓 연결
            try(Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT)) {
                // 입력 스트림 생성
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                // 서버로부터 Student 객체 읽어오기
                Student st = (Student) in.readObject();
                // 학생 정보 출력
                System.out.println(st.getName() + " " + st.getStudentNo());
                // 소켓과 스트림 닫기
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
