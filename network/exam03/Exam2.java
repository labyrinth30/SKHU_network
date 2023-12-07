package network.exam03;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exam2 implements Runnable {
    private String studentInfo;

    public Exam2(String studentInfo) {
        this.studentInfo = studentInfo;
    }

    @Override
    public void run() {
        System.out.println(studentInfo);
    }

    public static void main(String[] args) {
        // ExecutorService 생성
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // 10개의 스레드를 실행
        for (int i = 0; i < 10; i++) {
            String studentInfo = "201911016 이윤하";
            Exam2 examThread = new Exam2(studentInfo);

            // ExecutorService를 사용하여 스레드 실행
            executorService.execute(examThread);
        }

        // 작업이 완료된 후 ExecutorService 종료
        executorService.shutdown();
    }
}
