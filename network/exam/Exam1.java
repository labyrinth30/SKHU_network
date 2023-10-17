package network.exam;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class Exam1 {
/*
a.txt 파일을 생성하는 코드를 구현하라.

a.txt 파일의 내용은 자신의 소속, 학번, 이름이어야 한다.

파일 내용의 예:

IT융합자율학부 201014199 홍길동

위와 같이 3줄이어야 한다.

UTF-8 인코딩이어야 한다.
 */

    public static void main(String[] args) throws IOException {
        OutputStream out = new FileOutputStream("a.txt");
        OutputStreamWriter writer = new OutputStreamWriter(out, "UTF-8");
        writer.write("IT융합자율학부\n");
        writer.write("201014099\n");
        writer.write("홍길동\n");
        writer.close();
    }

    public static void mainA(String[] args) throws IOException {
        OutputStream out = new FileOutputStream("a.txt");
        Writer writer = new OutputStreamWriter(out, "UTF-8");
        writer.write("IT융합자율학부");
        writer.write("201014099");
        writer.write("홍길동");
        writer.close();
    }

    public static void mainB(String[] args) throws IOException {
        Writer writer = new OutputStreamWriter(new FileOutputStream("a.txt"), "UTF-8");
        writer.write("IT융합자율학부");
        writer.write("201014099");
        writer.write("홍길동");
        writer.close();
    }

    public static void mainC(String[] args) throws IOException {
        try (Writer writer = new OutputStreamWriter(new FileOutputStream("a.txt"), "UTF-8")) {
            writer.write("IT융합자율학부");
            writer.write("201014099");
            writer.write("홍길동");
        }
    }

}

