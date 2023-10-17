package network.exam;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

public class Exam4 {
//    a.txt 파일의 내용을 읽어서 c.txt.gz 파일에 저장하는 코드를 구현하라.
//
//    c.txt.gz 파일은 GZIP 압축되어야 한다.
//
//    buffering 해도 좋고 아니어도 좋다.
    public static void fileCopy(String sourceFile, String targetFile) throws IOException {
        InputStream in = new BufferedInputStream(new FileInputStream(sourceFile));
        // OutputStream out = new BufferedOutputStream(new FileOutputStream(targetFile));
        OutputStream out = new GZIPOutputStream(
                new BufferedOutputStream(new FileOutputStream(targetFile)));
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
        fileCopy("a.txt", "c.txt.gz");
    }

}

