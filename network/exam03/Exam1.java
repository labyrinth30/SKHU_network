package network.exam03;

public class Exam1 implements Runnable {
    private String studentInfo;

    public Exam1(String studentInfo) {
        this.studentInfo = studentInfo;
    }

    @Override
    public void run() {
        System.out.println(studentInfo);
    }

    public static void main(String[] args) {
        // 10개의 스레드를 생성하고 실행
        for (int i = 0; i < 10; i++) {
            String studentInfo = "201911016 이윤하";
            Exam1 examThread = new Exam1(studentInfo);
            Thread thread = new Thread(examThread);
            thread.start();
        }
    }
}
