package network.chap03;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
public class Example2a {
    public static void main(String[] args) throws IOException {
        var url = new URL("https://www.skhu.ac.kr");
        var connection = (HttpURLConnection)url.openConnection();
//        InputStream in = connection.getInputStream(); // 기존과 동일
//
//        Reader reader = new InputStreamReader(in); // Reader로 바꿔서 받기, 안 깨짐
        Reader reader = new InputStreamReader(connection.getInputStream()); // 헷갈리지 않게 이렇게 하기
        while (true) {
            int ch = reader.read();
            if (ch < 0) break;
            System.out.print((char)ch); // 유니코드 문자로 변환해서 리턴함.
        }
        reader.close();
    }
}