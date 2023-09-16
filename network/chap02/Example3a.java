package network.chap02;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
public class Example3a {
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
        InputStream in1 = new FileInputStream("ideo_3.jpg");
        OutputStream out1 = new FileOutputStream("ideo_3_copy.jpg");
        copyStream(in1, out1);
        // 원래의 복사 메소드, Buffering이 없어 큰 용량의 파일 처리에는 느리다.

        InputStream in2 = new BufferedInputStream(new FileInputStream("ideo_3.jpg"));
        OutputStream out2 = new BufferedOutputStream(new FileOutputStream("ideo_3_copy.jpg"));
        copyStream(in2, out2);
        // Buffer가 먼저 나옴(탕비실 냉장고) 다형성 구현
        // 버퍼에 데이터가 남아있다면 버퍼에서 데이터를 꺼내서 리턴한다.
        // 만약 남아있지 않다면 FileInputStream 객체의 read 메소드를 호출하여 데이터를 충분히 많이 읽어서
        // 읽은 데이터를 버퍼에 넣어두고, 그 중의 일부를 꺼내서 리턴한다.

        // BufferedInputStream과 FileInputStream을 헷갈리지 않고 호출하기 위해 필터링으로 구현함.
    }
}