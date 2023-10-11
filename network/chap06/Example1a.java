package network.chap06;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

public class Example1a {

    static Queue<Integer> queue = new ArrayDeque<Integer>();
    // 다형성 호출

    static class Producer extends Thread {
        final int count = 100;
        Random random = new Random();

        @Override
        public void run() {
            for (int i = 0; i < count; ++i) {
                int n = random.nextInt(count);
                synchronized(queue) {
                    queue.add(n);
                }
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

//    Producer 쓰레드는 정수를 100개만 add 하고 종료하는데,
//        Consumer 쓰레드는 계속해서 위 코드를 호출한다.
//        따라서 Consumer 쓰레드가 101 번째 queue.remove() 메소드를 호출하면
//        NoSuchElementException 에러가 발생한다.
