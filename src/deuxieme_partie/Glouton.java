package deuxieme_partie;

import java.util.Random;

import static deuxieme_partie.CombinaisonsSecretes.combinaisonsSecretes;

public class Glouton {

    public static void main(String[] args){
        CombinaisonsSecretes.genererCombinaisonsSecretes();
        Historique hist = new Historique();
        Score sc;

        // choisir une solution aléatoire
        Random rand = new Random();
        Proposition meilleur = new Proposition(combinaisonsSecretes.get(rand.nextInt(combinaisonsSecretes.size())));
        Proposition proposition;
        // Choisir l'evaluation et solution
        Evaluation eval = new Evaluation("1234");
        int i = 0, max;
        while(!eval.evaluer(meilleur)){
            hist.add(meilleur);
            max = -1;
            for(String s: combinaisonsSecretes) {
                proposition = new Proposition(s);
                sc = Score.calculerScore(hist, proposition);
                if(sc.c > max && !hist.contain(proposition)){
                    max = sc.c;
                    meilleur = proposition;
                    meilleur.b = sc.b;
                    meilleur.m = sc.m;
                }
            }
            i++;
            System.out.println("Meilleur " + i + " : -> " + meilleur.toString() + " apres  " + hist.history.size() + " coups.");
        }

        System.out.println(meilleur.toString());
    }

}
