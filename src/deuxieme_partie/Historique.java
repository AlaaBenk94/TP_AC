package deuxieme_partie;

import java.util.ArrayList;

public class Historique {

    public  ArrayList<Proposition> history;
    public  ArrayList<String> compatibles;


    public Historique(){
        history = new ArrayList<>();
        compatibles = new ArrayList<>();
    }

    public void add(Proposition p){
        if(contain(p))
            return;
        history.add(p);
        recalculerCompatibe(p);
    }

    public boolean contain(Proposition prop){
        for(Proposition p : history){
            if(p.s.equals(prop.s))
                return true;
        }
        return false;
    }

    private void recalculerCompatibe(Proposition p){
        ArrayList<String> list = CombinaisonsSecretes.nbCompatible(p);
        if(history.size() == 1)
            compatibles = list;
        else {
            compatibles = CombinaisonsSecretes.intersection(list, compatibles);
        }
    }



    public boolean empty(){
        return history.isEmpty();
    }

}
