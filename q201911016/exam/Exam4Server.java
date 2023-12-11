// Exam4Server.java
package q201911016.exam;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Exam4Server {
    public static void main(String[] args) {
        final int PORT = 10017;
        try (DatagramSocket serverSocket = new DatagramSocket(PORT)) {

            byte[] data = new byte[1024];
            DatagramPacket packet = new DatagramPacket(data, data.length);

            // 클라이언트로부터 데이터 수신
            serverSocket.receive(packet);

            ByteArrayInputStream bin = new ByteArrayInputStream(packet.getData(), 0, packet.getLength());
            ObjectInputStream in = new ObjectInputStream(bin);

            Student student = (Student)in.readObject();

            // 받은 정보를 서버 콘솔에 출력
            System.out.println(student.getStudentNo() + " " + student.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
