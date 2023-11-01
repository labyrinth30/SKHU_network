package network.chap09;


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class StudentServer {

    public static void main(String[] args) {
        final int PORT = 10003;
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                try (Socket socket = serverSocket.accept()) {
                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                    Student student = new Student("201911015",  "이윤하");
                    out.writeObject(student);
                    out.flush();
                } catch (Exception ex) {
                }
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}

