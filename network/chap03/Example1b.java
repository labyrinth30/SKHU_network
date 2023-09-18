package network.chap03;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
public class Example1b {
    public static void main(String[] args) throws IOException {
        Writer writer = new FileWriter("out.txt");
        writer.write("안녕하세요 여러분\n");
        writer.close();
        Reader reader = new FileReader("out.txt");
        while (true) {
            int ch = reader.read(); // -1 읽어야하니까 int로 선언, 이 안에는 유니코드 문자가 들어갔음.
            if (ch < 0) break;
            System.out.print((char)ch); // 타입캐스팅 해야함.
        }
        reader.close();
    }
}