package network.chap03;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
public class Example4c {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                new GZIPInputStream(new FileInputStream("out2.txt.gz"))));
        while (true) {
            String s = reader.readLine();
            if (s == null) break;
            System.out.println(s);
        }
        reader.close();
    }
}
//    한 줄씩 읽는 readLine 메소드가 Reader 클래스에 없고, BufferedReader 클래스에만 있다.
//        따라서 한 줄씩 읽으려면
//        BufferedReader 객체를 만들어야 하고
//        BufferedReader 타입의 변수로 readLine 메소드를 호출해야 한다.

//    BufferedReader 클래스의 readLine 메소드
//    줄바꿈 문자까지 한 줄을 읽어서 리턴한다.
//        리턴된 문자열에 줄바꿈 문자는 포함되지 않는다.
//        입력의 끝에서 더 이상 읽을 데이터가 없으면 null 값을 리턴한다.