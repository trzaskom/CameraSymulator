/**
 * Created by miki on 2018-10-20.
 */
public class Vector<T> {
    private T a;
    private T b;

    public Vector(T a, T b) {
        super();
        this.a = a;
        this.b = b;
    }

    public T getA() {
        return a;
    }

    public void setA(T a) {
        this.a = a;
    }

    public T getB() {
        return b;
    }

    public void setB(T b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "Vector [a=" + a + ", b=" + b + "]";
    }


}