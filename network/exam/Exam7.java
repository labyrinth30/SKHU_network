package network.exam;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exam7 {
//다음과 같이 자신의 소속, 학번, 이름을 출력하는 작업을
//
//3개의 실행흐름(thread)으로 실행하는 코드를 작성하라.
//
//IT융합자율학부 201014199 홍길동
//
//3개의 실행흐름이므로, 위 출력이 3번 반복되어서 출력 되어야 한다.
//
//출력 순서가 뒤섞이어도, 섞이지 않아도 좋다.
//
//ExecutorService 클래스를 이용해서 구현하라.
    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println("IT융합자율학부");
            System.out.println("201014199");
            System.out.println("홍길동");
        }
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(8);
        for (int i = 0; i < 3; ++i) {
            service.submit(new MyRunnable());
        }
        service.shutdown();
    }

}

