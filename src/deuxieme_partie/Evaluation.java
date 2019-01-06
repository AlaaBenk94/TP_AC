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

    public int[] evaluer(String prop) {
        int[] ret = new int[]{0,0};

        for(int i=0; i<prop.length(); i++) {
            int index = sol.indexOf(prop.charAt(i));
            if( index != -1){
                if(index == i)
                    ret[0]++;
                else
                    ret[1]++;
            }
        }

        System.out.println(prop + " (" + ret[0] + "," + ret[1] + ")");

        return null;
    }


    public static void main(String[] args){

        Evaluation e = new Evaluation("1234");

        e.evaluer("1258");
        e.evaluer("1253");
        e.evaluer("6857");
        e.evaluer("6457");
        e.evaluer("2413");
        e.evaluer("1234");

    }

}
