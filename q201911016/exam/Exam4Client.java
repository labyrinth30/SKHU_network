// Exam4Client.java
package q201911016.exam;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Exam4Client {
    public static void main(String[] args) {
        try (DatagramSocket clientSocket = new DatagramSocket()) {
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 10017;

            // 클라이언트에서 전송할 데이터
            Student student = new Student("201911016","이윤하");

            // Student 객체를 직렬화하여 byte 배열로 변환
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(student);
            out.flush();
            byte[] sendData = bos.toByteArray();

            // 서버로 데이터 전송
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
            clientSocket.send(sendPacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
