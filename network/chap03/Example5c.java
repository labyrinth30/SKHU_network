package network.chap03;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.zip.GZIPInputStream;
public class Example5c {
    public static void main(String[] args) throws IOException {
        Reader reader = new InputStreamReader(new GZIPInputStream(
                new BufferedInputStream(new FileInputStream("out2.txt.gz"))));
        Writer writer = new OutputStreamWriter(new BufferedOutputStream(System.out)); // 빠르게 출력
        while (true) {
            int ch = reader.read();
            if (ch < 0) break;
            writer.write(ch);
        }
        reader.close();
        writer.close();
    }
}
//        InputStreamReader: InputStream 객체에서 읽어온 데이터를 문자로 변환해서 리턴하는 Reader
//        OutputStreamWriter: 출력할 문자를 OutputStream 객체에 출력하기 위한 Writer