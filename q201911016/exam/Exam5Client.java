// Exam5Client.java
package q201911016.exam;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Exam5Client {
    public static void main(String[] args) {
        try (DatagramSocket clientSocket = new DatagramSocket()) {
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 10017;

            // Student 객체에 학번만 채워서 전송
            Student student = new Student("201911016");

            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bout);
            out.writeObject(student);
            out.flush();
            byte[] sendData = bout.toByteArray();

            // 서버로 데이터 전송
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
            clientSocket.send(sendPacket);


            // 서버로부터 데이터 수신
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            ByteArrayInputStream bin = new ByteArrayInputStream(receivePacket.getData(), 0, receivePacket.getLength());
            ObjectInputStream in = new ObjectInputStream(bin);

            Student studentFromServer = (Student) in.readObject();

            System.out.println(studentFromServer.getStudentNo() + " " + studentFromServer.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
