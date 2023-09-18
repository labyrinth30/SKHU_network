package network.chap03;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
public class Example3c {
    static void copyText(Reader reader, Writer writer) throws IOException {
        while (true) {
            int ch = reader.read();
            if (ch < 0) break;
            writer.write(ch);
        }
        reader.close();
        writer.close();
    }
    public static void main(String[] args) throws IOException {
        Reader reader = new InputStreamReader(new BufferedInputStream( // 연결 방식은 크게 성능에 차이 없음
                new FileInputStream("home.html")));
        Writer writer = new OutputStreamWriter(new BufferedOutputStream(
                new FileOutputStream("home_copy.html")));
        copyText(reader, writer);
    }
}
//    기능에서 큰 차이는 문자열을 한 줄씩 읽기 위한 readLine() 메소드가 오직 BufferedReader 클래스에만 있다는 점이다.