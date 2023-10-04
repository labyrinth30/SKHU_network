package network.chap05;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

class SumTaskK implements Callable<Long> {
//    계산 작업 결과 데이터를 메인 쓰레드에 리턴하기 위해서
//    Callable 인터페이스를 implements 하였다.

    int from, to;

    public SumTaskK(int from, int to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public Long call() throws Exception {
        long sum = 0;
        for (int i = from; i <= to; ++i)
            sum += i;
        return sum;
    }
}

public class Example3c {
    public static void main(String[] args)
            throws InterruptedException, ExecutionException {
        final int N = 10000;

        ExecutorService service = Executors.newFixedThreadPool(50);
        var futures = new ArrayList<Future<Long>>();
        // 리턴하는 데이터를 받을 Future 객체 생성
        for (int i = 0; i < N; ++i) {
            var future = service.submit(new SumTaskK(1, (i + 1) * 10000));
            futures.add(future);
            // 리턴한 Future 객체를 ArrayList에 넣음
        }
        service.shutdown();

        for (var future : futures)
            System.out.println(future.get());
        System.out.println("완료");
    }
}

