package deuxieme_partie;

import java.util.HashMap;

public class Proposition {

    public String s;
    public int b;
    public int m;



    public Proposition(String prop){
        s = prop;
        b = 0;
        m = 0;

    }

    @Override
    public String toString() {
        return s + " : (" + b + "," + m + ")";
    }
}
