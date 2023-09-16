package network.chap02;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;
public class Example4a {
    static void copyStream(InputStream in, OutputStream out) throws IOException {
        while (true) {
            int b = in.read();
            if (b < 0) break;
            out.write(b);
        }
        in.close();
        out.close();
    }
    public static void main(String[] args) throws IOException {
        OutputStream out = new BufferedOutputStream(new FileOutputStream("out.txt"));
        byte[] a = new byte[] { 'h', 'e', 'l', 'l', 'o', ' ', 'w', 'o', 'r', 'l', 'd', '\n' };
        for (int i = 0; i < 10000; ++i)
            out.write(a);
        out.close();
        InputStream in1 = new BufferedInputStream(new FileInputStream("out.txt"));
        OutputStream out1 = new BufferedOutputStream(new FileOutputStream("out1.txt"));
        copyStream(in1, out1);
        InputStream in2 = new BufferedInputStream(new FileInputStream("out.txt"));
        OutputStream out2 = new GZIPOutputStream(new BufferedOutputStream(new FileOutputStream("out2.txt.gz")));
        copyStream(in2, out2);
    }
}
//        DeflaterOutputStream
//        이 클래스의 압축 알고리즘은 PNG 그림 파일의 압축 알고리즘과 같다.
//        이 알고리즘으로 압축된 파일들이 공통으로 사용하는 확장자는 없다.

//        GZIPOutputStream
//        이 클래스의 압축 알고리즘은 gzip 이다.
//        이 알고리즘으로 압축된 파일들은 다음과 같은 확장자를 사용한다.
//        *.gz, *.gzip, *.tgz, *.tar.gz

//        ZIPOutputStream
//        이 클래스의 압축 알고리즘은 zip 이다.
//        이 알고리즘으로 압축된 파일들은 다음과 같은 확장자를 사용한다.
//        *.zip

//        파일 묶음
//        *.tgz 파일이나 *.zip 파일은 여러 파일들이 묶여서 압축된 파일이다.
//        *.gz 파일이나, *.gzip 파일은 파일 한 개가 압축된 것이다.