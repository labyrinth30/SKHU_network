package network.chap04;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
public class Example2a {
    static void copyStream(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        while (true) {
            int count = in.read(buffer);
            if (count < 0) break;
            out.write(buffer);
        }
    }
    public static void main(String[] args) throws IOException {
        String[] filePathList = {
                "zip_test/naver.html",
                "zip_test/naver_news.html",
                "zip_test/naver_map.html"
        };
        var out = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream("test.zip"))); // 파일 저장용 outputstream
        for (String filePath : filePathList) {
            out.putNextEntry(new ZipEntry(filePath)); // putNextEntry 메서드, 이름 포함 경로명, 마지막 수정 시간들을 기록
            var in = new BufferedInputStream(new FileInputStream(filePath));
            copyStream(in, out);
            in.close();
        }
        out.close();
    }
}
