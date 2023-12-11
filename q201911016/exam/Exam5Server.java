// Exam5Server.java
package q201911016.exam;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Exam5Server {
    public static void main(String[] args) {
        final int PORT = 10017;
        try (DatagramSocket serverSocket = new DatagramSocket(PORT)) {

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);

            ByteArrayInputStream bin = new ByteArrayInputStream(receivePacket.getData(), 0, receivePacket.getLength());
            ObjectInputStream in = new ObjectInputStream(bin);

            Student studentFromClient = (Student) in.readObject();
            // 이름을 채워서 전송
            studentFromClient.setName("이윤하");

            InetAddress clientAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();

            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bout);
            out.writeObject(studentFromClient);
            out.flush();
            byte[] sendData = bout.toByteArray();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
            serverSocket.send(sendPacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
