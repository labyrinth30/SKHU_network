package network.chap05;
public class Example1a {
    static class TestThread extends Thread {
        String message;
        public TestThread(String message) {
            this.message = message;
        }
        @Override
        public void run() { // run 메서드 오버라이드
            for (int count = 0; count < 4; ++count)
                System.out.printf("%s %d\n", message, count); // 쓰레드 끝나는 순간
        }
    }
    public static void main(String[] args) { // main 실행 흐름도 쓰레드 실행 흐름임
        Thread threadA = new TestThread("threadA"); // Java 객체일 뿐 실행 흐름은 아님. 실행 흐름을 만들기 위한 객체임
        threadA.start(); // 실행 흐름이 만들어지는 순간
        Thread threadB = new TestThread("threadB");
        threadB.start();
        new TestThread("threadC").start(); // 객체를 생성하자마자 바로 메소드 호출 가능함.
    }
}