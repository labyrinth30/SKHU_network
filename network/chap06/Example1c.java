package network.chap06;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

public class Example1c {

    static Queue<Integer> queue = new ArrayDeque<Integer>();

    static class Producer extends Thread {
        final int count = 100;
        Random random = new Random();

        @Override
        public void run() {
            try {
                for (int i = 0; i < count; ++i) {
                    Thread.sleep(10); // 통신 작업 흉내
                    int n = random.nextInt(100);
                    synchronized(queue) {
                        queue.add(n);
                    }
                }
                synchronized(queue) {
                    queue.add(-1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Consumer extends Thread {
        Random random = new Random();

        @Override
        public void run() {
            try {
                int n;
                while (true) {
                    Thread.sleep(10);
                    synchronized(queue) {
                        n = queue.remove();
                    }
                    if (n < 0) break;
                    int sum = 0;
                    for (int i = 1; i <= n; ++i)
                        sum += i;
                    System.out.printf("%d: %d\n", n, sum);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Producer().start();
        new Consumer().start();
    }
}

//    위 예제에서 Producer Consumer 둘 다 10 밀리초씩 sleep 한다.
//        그리고 Producer 쓰레드를 먼저 start 했다.
//        그렇지만 Producer 쓰레드가 Consumer 쓰레드보다 약간 먼저 실행된다는 보장이 없고,
//        두 쓰레드가 10 밀리초씩 sleep 하며 동일한 속도로 실행된다는 보장이 없다.
//        만약 Consumer 쓰레드가 약간 빠르다면 queue가 비게 되고, 위와 같은 에러가 발생한다.
//
//        Consumer는 queue가 비어있다면, queue에 새 항목이 채워질 때까지 wait 해야 한다.
//        그리고 Producer는 queue 새 항목을 추가하자 마자 notify 해야 한다.
