package deuxieme_partie;

public class Score {

    public int n;
    public int k;

    public Score(int k, int n){
        this.k = k;
        this.n = n;
    }



    public static int calculerScore(Historique hist, Proposition prop){



        return 0;
    }

    public static void main(String[] args){
        CombinaisonsSecretes cs = CombinaisonsSecretes.genererCombinaisonsSecretes(-1, -1, new Evaluation("1234"));
        System.out.println(cs.nbCompatible(0, 0));
        System.out.println(cs.nbCompatible(1, 2));
        System.out.println(cs.nbCompatible(2, 1));
        System.out.println(cs.nbCompatible(4, 0));
        System.out.println(cs.nbCompatible(0, 4));
    }

}
