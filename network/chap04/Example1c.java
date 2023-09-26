package network.chap04;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
public class Example1c {
    static void download(String url, String path) throws IOException {
        var urlObj = new URL(url);
        var connection = (HttpURLConnection)urlObj.openConnection();
        try (
                InputStream in = new BufferedInputStream(connection.getInputStream());
                OutputStream out = new BufferedOutputStream(new FileOutputStream(path)); // 마지막 세미콜론 생략 가능함
        ) {
            while (true) {
                int b = in.read();
                if (b < 0) break;
                out.write(b);
            }
            connection.disconnect(); // try with resources 구문으로 인해 따로 close 하지 않음
                                    // fianlly 써야함
        }
    }
    public static void main(String[] args) throws IOException {
        File directory = new File("zip_test");
        if (directory.exists() == false)
            directory.mkdir();
        download("https://www.naver.com", "zip_test/naver.html");
        download("https://news.naver.com", "zip_test/naver_news.html");
        download("https://map.naver.com", "zip_test/naver_map.html");
    }
}