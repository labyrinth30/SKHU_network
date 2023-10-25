package network.chap08;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PortScanner4 {

    static class PortTask implements Runnable {
        String host;
        int port, timeout;

        public PortTask(String host, int port, int timeout) {
            this.host = host;
            this.port = port;
            this.timeout = timeout;
        }

        @Override
        public void run() {
            try (Socket socket = new Socket()) {
                SocketAddress address = new InetSocketAddress(host, port);
                socket.connect(address, timeout);
                synchronized (System.out) {
                    System.out.printf("\n\n\n%s %d 연결 성공\n", host, port);

                    InputStream in = socket.getInputStream();
                    OutputStream out = socket.getOutputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
                    writer.write("GET / HTTP/1.1\r\n");
                    writer.write("User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:20.0)\r\n");
                    writer.write("    Gecko/20100101 Firefox/20.0\r\n");
                    writer.write("Host: www.google.com\r\n");
                    writer.write("Connection: keep-alive\r\n");
                    writer.write("Accept-Language: en-US,en;q=0.5\r\n");
                    writer.write("Accept-Encoding: gzip, deflate\r\n");
                    writer.write("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n");
                    writer.write("\r\n");
                    writer.write("\r\n");
                    writer.flush();
                    while (true) {
                        String s = reader.readLine();
                        if (s == null) break;
                        System.out.println(s);
                    }
                }

            } catch (Exception e) {
                // 연결할 수 없다
            }
        }
    }

    public static void main(String[] args) {
        String host = "www.skhu.ac.kr";
        int timeout = 300;
        int portFrom = 1, portTo = 10000;

        ExecutorService executor = Executors.newFixedThreadPool(1000);
        for (int port = portFrom; port <= portTo; ++port) {
            executor.submit(new PortTask(host, port, timeout));
        }
        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
        }
        System.out.println("DONE");
    }

}

