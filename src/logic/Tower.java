package logic;

import java.util.ArrayDeque;

import java.util.Deque;

public class Tower {
    private Deque<Aro> torre;

    public Tower() {
        torre = new ArrayDeque<Aro>();
    }

    public boolean isEmpty() {
        return torre.isEmpty();
    }

    public void setAro(Aro a) {
        if (torre.isEmpty() || a.getSize() < torre.peek().getSize()) {
            torre.push(a);
        }
    }

    public Aro moverAro() {
        return torre.pop();
    }

    public Deque<Aro> getTorre() {
        return torre;
    }
}