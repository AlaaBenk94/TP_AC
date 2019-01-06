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

    /**
     * Evaluer par rapport a la solution
     * @param prop
     */
    public boolean evaluer(Proposition prop) {

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

        if(prop.b == sol.length() && prop.m == 0)
            return true;
        return false;

    }

    /**
     * Evaluer par rapport a une solution donnée
     * @param prop
     */
    public static int[] evaluer(String prop, Proposition target) {

        int b = 0;
        int m = 0;

        for(int i=0; i<prop.length(); i++) {
            int index = target.s.indexOf(prop.charAt(i));
            if( index != -1){
                if(index == i)
                    b++;
                else
                    m++;
            }
        }

        return new int[]{b,m};

    }


    public static void main(String[] args){

        Evaluation e = new Evaluation("1234");


    }

}
