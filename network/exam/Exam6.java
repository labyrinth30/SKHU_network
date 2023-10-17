package network.exam;

public class Exam6 {
//다음과 같이 자신의 소속, 학번, 이름을 출력하는 작업을
//
//3개의 실행흐름(thread)으로 실행하는 코드를 작성하라.
//
//IT융합자율학부 201014199 홍길동
//
//3개의 실행흐름이므로, 위 출력이 3번 반복되어서 출력 되어야 한다.
//
//출력 순서가 뒤섞이어도, 섞이지 않아도 좋다.
//
//Runnable 인터페이스를 implements해서 작업 클래스를 구현하라.
    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println("IT융합자율학부");
            System.out.println("201014199");
            System.out.println("홍길동");
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3; ++i) {
            Thread thread = new Thread(new MyRunnable());
            thread.start();
        }
    }

}

