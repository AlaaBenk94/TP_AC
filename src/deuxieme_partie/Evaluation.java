package deuxieme_partie;

public class Evaluation {

    String sol;

    public Evaluation(String sol){
        this.sol = sol;
    }

    public void setSol(String sol) {
        this.sol = sol;
    }

    public String getSol() {
        return sol;
    }

    public void evaluer(Proposition prop) {

        prop.b = 0;
        prop.m = 0;

        for(int i=0; i<prop.s.length(); i++) {
            int index = sol.indexOf(prop.s.charAt(i));
            if( index != -1){
                if(index == i)
                    prop.b++;
                else
                    prop.m++;
            }
        }
    }


    public static void main(String[] args){

        Evaluation e = new Evaluation("1234");
        Proposition k;
        e.evaluer(k = new Proposition("1258"));
        System.out.println(k.toString());
        e.evaluer(k = new Proposition("1253"));
        System.out.println(k.toString());
        e.evaluer(k = new Proposition("6857"));
        System.out.println(k.toString());
        e.evaluer(k = new Proposition("6457"));
        System.out.println(k.toString());
        e.evaluer(k = new Proposition("2413"));
        System.out.println(k.toString());
        e.evaluer(k = new Proposition("1234"));
        System.out.println(k.toString());

    }

}
