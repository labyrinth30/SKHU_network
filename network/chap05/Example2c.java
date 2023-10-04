package network.chap05;

class SumTaskC implements Runnable {
    int from, to;
    long result;

    public SumTaskC(int from, int to) {
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

public class Example2c {
    public static void main(String[] args) {
        int from = 1, to = 50000000;
        SumTaskC sumTask = new SumTaskC(from, to);
        Thread thread = new Thread(sumTask);
        thread.start();
        System.out.print(from + " 부터 " + to + " 까지 합계는 ");
        System.out.print(sumTask.getResult());
    }
}

// 스레드끼리 공유하지 않는 영역이 스택
// 공유하는 영역이 힙 영역
// 그러므로 값 전달은 배열과 객체로 공유한다.
// 계산이 느려서 0으로 출력됨.