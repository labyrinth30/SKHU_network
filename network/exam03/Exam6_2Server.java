package network.exam03;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exam6_2Server {
    public static void main(String[] args) {
        final int PORT = 10016;
        try(ServerSocket serverSocket = new ServerSocket(PORT)) {
            ExecutorService executor = Executors.newFixedThreadPool(200);
            while(true){
                try{
                    Socket socket = serverSocket.accept();
                    executor.submit(new StudentTask(socket));
                } catch (IOException e) {
                    System.err.println(e);
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    static class StudentTask implements Runnable{
        Socket socket;
        public StudentTask(Socket socket){
            this.socket = socket;
        }
        @Override
        public void run() {
            //클라이언트가 연결되면
            //자신의 학번과 이름을 채운 Student 객체를 클라이언트에 전송한다.
            //연결을 끊는다.
            //다른 클라이언트 연결에 대해서, 위 작업을 계속 반복한다.
            try(Socket autoClose = socket){
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                Student st = new Student("201911016", "이윤하");
                out.writeObject(st);
                out.flush();
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }
}
