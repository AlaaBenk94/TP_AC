package deuxieme_partie;

public class Score {

    public int b;
    public int m;
    public int c;

    public String toString(){
        return "(" + b + "," + m + "," + c + ")";
    }

    public static Score calculerScore(Historique hist, Proposition prop){
        Score score = new Score();
        score.c = 0;
        int s=0;

        for(Proposition p : hist.history){
            prop.b = p.b;
            prop.m = p.m;
            s = CombinaisonsSecretes.intersection(CombinaisonsSecretes.nbCompatible(prop),hist.compatibles).size();
            if(s > score.c) {
                score.b = p.b;
                score.m = p.m;
                score.c = s;
            }
        }

        return score;
    }

    public static void main(String[] args){
        CombinaisonsSecretes cs = CombinaisonsSecretes.genererCombinaisonsSecretes(-1, -1);
        Historique hist = new Historique();
        Evaluation eval = new Evaluation("12");
        Proposition p = new Proposition("45");
        p = new Proposition("45");
        eval.evaluer(p);
        System.out.println(p.toString());
        hist.add(p);

        p = new Proposition("43");
        eval.evaluer(p);
        System.out.println(p.toString());
        hist.add(p);

        System.out.println(hist.history.toString());
        System.out.println(hist.compatibles.size());

        for (String s : CombinaisonsSecretes.combinaisonsSecretes){
            Score score = Score.calculerScore(hist, new Proposition(s));
            System.out.println(s + " => " + score.toString());
        }

    }

}
