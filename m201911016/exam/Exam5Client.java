package network.chap01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Exam5Client {

    public static void main(String[] args) {
        final String HOST = "localhost";
        final int PORT = 12349;

        try (Socket socket = new Socket(HOST, PORT)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message = reader.readLine();
            System.out.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
