package network.chap09;

import java.io.Serializable;

public class Gugu implements Serializable {
    private static final long serialVersionUID = 1L;
    int a;
    int b;
    int result;

    public Gugu(int a, int b) {
        super();
        this.a = a;
        this.b = b;
    }

    // getter와 setter
    public int getA() {
        return a;
    }
    public int setA(int a) {
        return this.a = a;
    }
    public int getB() {
        return b;
    }
    public int setB(int b) {
        return this.b = b;
    }
    public int getResult() {
        return result;
    }
    public int setResult(int result) {
        return this.result = result;
    }

}
