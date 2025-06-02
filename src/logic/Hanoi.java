package logic;

import java.util.ArrayList;

public class Hanoi {
    private ArrayList<Tower> torres;
    private Integer numDiscos;

    public Hanoi(Integer numDiscos) {
        torres = new ArrayList<Tower>();
        Tower A = new Tower();
        Tower B = new Tower();
        Tower C = new Tower();
        torres.add(A);
        torres.add(B);
        torres.add(C);
        this.numDiscos = numDiscos;

        for (int i = this.numDiscos; i > 0; i--) {
            Aro aro = new Aro(i);
            torres.get(0).getTorre().push(aro);
        }
    }

    public ArrayList<Tower> getTorres() {
        return torres;
    }

    public boolean completado() {
        return torres.get(0).isEmpty() && torres.get(1).isEmpty();
    }

    public boolean moverDisco(int origen, int destino) {
        if (origen < 0 || origen >= 3 || destino < 0 || destino >= 3 || torres.get(origen).isEmpty()) {
            return false;
        }
        if (torres.get(destino).getTorre().isEmpty() ||
                torres.get(origen).getTorre().peek().getSize() < torres.get(destino).getTorre().peek().getSize()) {
            torres.get(destino).getTorre().push(torres.get(origen).getTorre().pop());
            return true;
        }
        return false;
    }

    public void reiniciar(int numDiscos) {
        torres.clear();
        Tower A = new Tower();
        Tower B = new Tower();
        Tower C = new Tower();
        torres.add(A);
        torres.add(B);
        torres.add(C);
        this.numDiscos = numDiscos;

        for (int i = this.numDiscos; i > 0; i--) {
            Aro aro = new Aro(i);
            torres.get(0).getTorre().push(aro);
        }
    }

    public int getNumDiscos() {
        return numDiscos;
    }
}