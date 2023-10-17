package network.exam;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Exam3 {
//a.txt 파일의 내용을 읽어서 b.txt 파일에 저장하는 코드를 구현하라.
//즉 a.txt 파일을 b.txt 파일로 복사하는 코드를 구현하라.
//buffering 하면서 읽고 쓰고 해야 한다.
//buffering을 하지 않고 구현하면 크게 감점.
    public static void fileCopy(String sourceFile, String targetFile) throws IOException {
        InputStream in = new BufferedInputStream(new FileInputStream(sourceFile));
        OutputStream out = new BufferedOutputStream(new FileOutputStream(targetFile));
        byte[] a = new byte[1024];
        while (true) {
            int count = in.read(a);
            if (count < 0) break;
            out.write(a, 0, count);
        }
        in.close();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        fileCopy("a.txt", "b.txt");
    }

}

