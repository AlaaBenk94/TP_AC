package deuxieme_partie;

import java.util.HashMap;

public class Historique {

    private HashMap<String, Proposition> history;

    public Historique(){
        history = new HashMap<>();
    }

    public void add(Proposition p) {
        history.put(p.s, p);
    }

    public void remove(Proposition p) {
        history.remove(p.s);
    }

}
