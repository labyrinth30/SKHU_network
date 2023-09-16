package network.chap02;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
public class Example2c {
    static void copyStream(InputStream in, OutputStream out) throws IOException {
        while (true) {
            int b = in.read();
            if (b < 0) break;
            out.write(b);
        }
        in.close();
        out.close();
    }
    // 다형성 구현으로 인해 자식 객체도 사용 가능
    public static void main(String[] args) throws IOException {
        var url = new URL("https://www.skhu.ac.kr/sites/skhu/images/sub/ideo_3.jpg");
        var connection = (HttpURLConnection)url.openConnection();
        InputStream in = connection.getInputStream();
        FileOutputStream out = new FileOutputStream("ideo_3.jpg");
        copyStream(in, out);
    }
}