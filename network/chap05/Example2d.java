package network.chap05;

class SumTaskD implements Runnable {
    int from, to;
    long result = -1;

    public SumTaskD(int from, int to) {
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

public class Example2d {
    public static void main(String[] args) throws InterruptedException {
        int from = 1, to = 50000000;
        SumTaskD sumTask = new SumTaskD(from, to);
        Thread thread = new Thread(sumTask);
        thread.start();
        while (sumTask.getResult() == -1)
            Thread.sleep(100);
        System.out.print(from + " 부터 " + to + " 까지 합계는 ");
        System.out.print(sumTask.getResult());
    }
}

// 계산 결과가 result 멤버 변수에 대입될 때까지
// 메인 쓰레드가 정해진 시간 동안 대기(sleep)하는 코드를 추가하였다.
// Thread.sleep 메소드의 파라미터는 대기할 시간 밀리초이다.
// sleep 메소드를 호출한 쓰레드는 그 시간 동안 대기(sleep) 상태가 된다.
// 쓰레드가 sleep 상태인 동안에는 CPU core가 그 쓰레드를 실행하지 않는다.
// 다른 쓰레드의 작업이 끝났는지 아닌지를 알기 위해,
// 이렇게 반복문에서 변수 값이 변하는지 보면서 기다리는 구현 기법을 polling 이라고 한다.
// 좋은 방법이 아니다.
