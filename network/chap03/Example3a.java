package network.chap03;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
public class Example3a {
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
        Reader reader = new BufferedReader(new FileReader("home.html"));
        Writer writer = new BufferedWriter(new FileWriter("home_copy.html"));
        copyText(reader, writer);
    }
}