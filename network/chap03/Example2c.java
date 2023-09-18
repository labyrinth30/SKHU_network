package network.chap03;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
public class Example2c {
    static void copyText(Reader reader, Writer writer) throws IOException { // copyStream과 동일
        while (true) {
            int ch = reader.read();
            if (ch < 0) break;
            writer.write(ch);
        }
        reader.close();
        writer.close();
    }
    public static void main(String[] args) throws IOException {
        var url = new URL("https://www.skhu.ac.kr");
        var connection = (HttpURLConnection)url.openConnection();
        Reader reader = new InputStreamReader(connection.getInputStream());
        Writer writer = new FileWriter("home.html");
        copyText(reader, writer);
    }
}
//    static void copyText(Reader reader, Writer writer) throws IOException {
//        이 메소드는 Reader 객체에서 읽은 문자들을 Writer 객체에 출력한다.
//                따라서 이 메소드는 문자열 텍스트를 복사할 때만 사용할 수 있다.

//        static void copyStream(InputStream in, OutputStream out) throws IOException {
//            이 메소드는 InputStream 객체에서 읽은 데이터를 그대로 OutputStream 객체에 출력한다.
//            그 데이터가 문자열 텍스트이어도, 사진 이미지이어도 복사된다.

//            따라서 원본 데이터를 그대로 복사할 때는, copyText 메소드보다 copyStream 메소드가 좋다.