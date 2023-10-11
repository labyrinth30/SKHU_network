package network.chap06;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

public class Example1b {

    static Queue<Integer> queue = new ArrayDeque<Integer>();

    static class Producer extends Thread {
        final int count = 100;
        Random random = new Random();

        @Override
        public void run() {
            for (int i = 0; i < count; ++i) {
                int n = random.nextInt(100);
                synchronized(queue) {
                    queue.add(n);
                }
            }
            synchronized(queue) { // 끝을 알리는 코드
                queue.add(-1);
            }
        }
    }

    static class Consumer extends Thread {
        Random random = new Random();

        @Override
        public void run() {
            int n;
            while (true) {
                synchronized(queue) {
                    n = queue.remove();
                }
                if (n < 0) break; // 꺼낸 값이 -1이면 빠져나가게 함
                int sum = 0;
                for (int i = 1; i <= n; ++i)
                    sum += i;
                System.out.printf("%d: %d\n", n, sum);
            }
        }
    }

    public static void main(String[] args) {
        new Producer().start();
        new Consumer().start();
    }
}

