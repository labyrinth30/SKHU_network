package network.chap02;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPOutputStream;

public class HwA201911016 {
    static void copyStream(InputStream in, OutputStream out) throws IOException {
        while (true) {
            int b = in.read();
            if (b < 0) break;
            out.write(b);
        }
        in.close();
        out.close();
    }
    public static void main(String[] args) throws IOException {
        var url = new URL("https://www.naver.com"); // 웹에서 읽어서 파일로 출력함
        var connection = (HttpURLConnection)url.openConnection();
        InputStream in = new BufferedInputStream(connection.getInputStream());
        OutputStream out = new GZIPOutputStream(new BufferedOutputStream(new FileOutputStream("naver.html.gz")));
        copyStream(in, out);

        InputStream in2 = new BufferedInputStream(connection.getInputStream());
        OutputStream out2 = new BufferedOutputStream(new FileOutputStream("naver.html"));
        copyStream(in2, out2);
    }
}