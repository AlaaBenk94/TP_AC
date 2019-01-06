package deuxieme_partie;

import java.util.ArrayList;

public class Historique {

    private ArrayList<Proposition> history;

    public Historique(){
        history = new ArrayList<>();
    }

    public void add(Proposition p) {
        history.add(p);
    }

    public void remove(Proposition p) {
        history.remove(p);
    }

}
