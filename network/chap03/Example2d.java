package network.chap03;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
public class Example2d {
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
        Reader reader = new InputStreamReader(new FileInputStream("home.html"), "UTF-8");
        Writer writer = new OutputStreamWriter(new FileOutputStream("home2.html"), "euc-kr");
        copyText(reader, writer);
    }
}
//        "home.html" 파일에서 문자를 읽는 Reader 객체를 생성하는 코드는 다음과 같이 구현할 수 있다.
//        (1) Reader reader = new FileReader("home.html");
//        (2) Reader reader = new InputStreamReader(new FileInputStream("home.html"));
//        위 두 구현에 큰 차이 없으니, 편한 방식으로 구현하면 된다.

//        위 두 구현의 차이는 다음과 같다.
//        (1)번 FileReader는 유니코드와 같은 표준 문자 인코딩 파일을 읽을 때만 사용할 수 있고
//        "euc-kr" 인코딩 파일을 읽을 때는 사용할 수 없다.
//        (2)번 InputStreamReader는 "UTF-8" 같은 유니코드 표준 문자 인코딩 파일 뿐만 아니라
//        "euc-kr" 인코딩 파일을 읽을 때도 사용할 수 있다.

//        Reader reader = new InputStreamReader(new FileInputStream("파일명"), "인코딩");
//        FileWriter 클래스와 OutputStreamWriter 클래스의 차이도 마찬가지이다.
//        Writer writer = new OutputStreamWriter(new FileOutputStream("파일명"), "인코딩");