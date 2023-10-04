package network.chap05;

class SumTaskE implements Runnable {
    int from, to;
    long result;

    public SumTaskE(int from, int to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public void run() {
        long sum = 0;
        for (int i = from; i <= to; ++i)
            sum += i;
        result = sum;
    }

    public long getResult() {
        return result;
    }
}

public class Example2e {
    public static void main(String[] args) throws InterruptedException {
        int from = 1, to = 50000000;
        SumTaskE sumTask = new SumTaskE(from, to);
        Thread thread = new Thread(sumTask);
        thread.start();
        thread.join();
        System.out.print(from + " 부터 " + to + " 까지 합계는 ");
        System.out.print(sumTask.getResult());
    }
}

// thread.join();
//        메인 쓰레드가 이 메소드를 호출하면,
//        바로 위에서 이 thread 객체로 생성한 새 실행흐름이 종료할 때까지,
//        메인 쓰레드는 대기(sleep)하게 된다.
//        그 실행흐름이 이미 종료했다면, 메인 쓰레드는 대기하지 않고 바로 리턴한다.
