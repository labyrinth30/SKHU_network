package network.chap06;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

public class Example1d {

    static Queue<Integer> queue = new ArrayDeque<Integer>();

    static class Producer extends Thread {
        final int count = 100;
        Random random = new Random();

        @Override
        public void run() {
            try {
                for (int i = 0; i < count; ++i) {
                    Thread.sleep(10);
                    int n = random.nextInt(100);
                    synchronized(queue) {
                        queue.add(n);
                        queue.notify();
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
                    synchronized(queue) {
                        if (queue.size() == 0)
                            queue.wait();
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
        for (int i = 0; i < 1; ++i)
            new Consumer().start();
        for (int i = 0; i < 1; ++i)
            new Producer().start();
    }
}

//    Consumer는 queue가 비어있다면, queue에 새 항목이 채워질 때까지 wait 해야 한다.
//        그리고 Producer는 queue 새 항목을 추가하자 마자 notify 해야 한다.
//queue가 비게 된 후 wait 메소드 호출한 Consumer 쓰레드들은 대기 상태에 빠진다.
//        Producer가 값을 하나 추가하고 notify 하면, 대기 상태의 Consumer 쓰레드들이 전부 깨어난다.
//        그 중 가장 먼저 깨어난 쓰레드가 remove하면, queue는 다시 비게 된다.
//        약간 늦게 깨어난 쓰레드가 또 remove 하면, NoSuchElementException 에러가 발생한다.
//
//        따라서 깨어난 Consumer 쓰레드는, 한 발 늦게 깨어난 것일 수도 있으니,
//        queue가 다시 비어있게 되지는 않았는지 다시 확인해야 한다.
