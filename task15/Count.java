package task15;
public class Count implements AutoCloseable {
    static int iterator;
    {
        iterator = 0;
    }

    public void add() {
        iterator=iterator+1;
    }

    @Override
    public void close() {
        System.out.println("now closed");
    }
}
