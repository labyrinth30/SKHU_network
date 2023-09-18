package network.chap03;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
public class Example1a {
    public static void main(String[] args) throws IOException {
        final String filePath = "out.txt";
        Writer writer = new FileWriter(filePath);
        writer.write("안녕하세요\n");
        writer.close();
        Reader reader = new FileReader(filePath);
        char[] buffer = new char[100];
        while (true) {
            int count = reader.read(buffer);
            if (count < 0) break;
            for (int i = 0; i < count; ++i)
                System.out.print(buffer[i]);
        }
        reader.close();
    }
}