package network.exam;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Exam2 {
// a.txt 파일의 내용을 읽어서 화면에 출력하는 코드를 구현하라.
    public static String readFile(String filePath, String encoding) throws IOException {
        InputStream in = new FileInputStream(filePath);
        InputStreamReader reader = new InputStreamReader(in, encoding);
        StringBuilder builder = new StringBuilder();
        char[] a = new char[1024];
        while (true) {
            int count = reader.read(a);
            if (count == -1) break;
            builder.append(a, 0, count);
        }
        reader.close();
        return builder.toString();
    }

    public static void main(String[] args) throws IOException {
        String s = readFile("a.txt", "UTF-8");
        System.out.println(s);
    }
}

