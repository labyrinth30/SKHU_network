package network.chap03;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
public class Example2b {
    public static void main(String[] args) throws IOException {
        var url = new URL("https://www.skhu.ac.kr");
        var connection = (HttpURLConnection)url.openConnection();
        Reader reader = new InputStreamReader(connection.getInputStream()); // 이렇게 줄여서 하기
        while (true) {
            int ch = reader.read();
            if (ch < 0) break;
            System.out.print((char)ch);
        }
        reader.close();
    }
}
//    그런데 만약 웹페이지의 문자 인코딩이 euc-kr 이라면, 한글이 깨져서 출력될 것이다.
//        다음과 같이 문자 인코딩을 지정해 줄 수 있다.
//        Reader reader = new InputStreamReader(connection.getInputStream(), "euc-kr");
//        Reader reader = new InputStreamReader(connection.getInputStream(), "UTF-8");
//  UTF-8은 8비트로 저장되면(8비트, 소스코드 등) 8비트로, 8비트로 저장 안 되면 16비트로 저장함
//  UTF-16은 무조건 16비트로 저장함