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
public class Example2b {
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
        try (
                var out = new ZipOutputStream(
                        new BufferedOutputStream(new FileOutputStream("test.zip"))); // out.close() 위해서 선언
        ) {
            for (String filePath : filePathList) {
                out.putNextEntry(new ZipEntry(filePath));
                try ( var in = new BufferedInputStream(new FileInputStream(filePath)); ) { // in.close() 위해서 선언
                    copyStream(in, out);
                }
            }
        }
    }
}