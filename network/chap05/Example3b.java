package network.chap05;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class SumTaskJ implements Runnable {
    int from, to;

    public SumTaskJ(int from, int to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public void run() {
        long sum = 0;
        for (int i = from; i <= to; ++i)
            sum += i;
        System.out.println(sum);
    }
}

public class Example3b {
    public static void main(String[] args) throws InterruptedException {
        final int N = 10000;

        ExecutorService service = Executors.newFixedThreadPool(50); // 50개 만듦
        for (int i = 0; i < N; ++i)
            service.submit(new SumTaskJ(1, (i + 1) * 10000));
        // 처리해야 할 작업을 제출한다.
        // 제출된 Runnable 객체의 run 메소드에 작업이 구현되어 있다.
        service.shutdown();
        // 제출된 작업 완료 후 스레드 자동 종료함.

        service.awaitTermination(60, TimeUnit.SECONDS);
        // 60초 내에 처리 안 되면 exception 호출함ㄷ
        System.out.println("완료");
    }
}

