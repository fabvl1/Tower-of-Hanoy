package logic;

public class Aro {
    private int size;

    public Aro(Integer size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return String.valueOf(size);
    }
}