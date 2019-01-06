package deuxieme_partie;

import java.util.Random;

import static deuxieme_partie.CombinaisonsSecretes.combinaisonsSecretes;

public class Glouton {

    public static void main(String[] args){
        CombinaisonsSecretes.genererCombinaisonsSecretes(-1, -1);
        Historique hist = new Historique();
        Score sc = new Score();

        // choisir une solution aléatoire
        Random rand = new Random();
        Proposition meilleur = new Proposition(combinaisonsSecretes.get(rand.nextInt(combinaisonsSecretes.size())));
        Proposition proposition;
        // Choisir l'evaluation et solution
        Evaluation eval = new Evaluation("12");
        int i = 0, max;
        while(!eval.evaluer(meilleur)){
            hist.add(meilleur);
            System.out.println("Iteration : " + (i) );
            max = 0;
            for(String s: combinaisonsSecretes) {

                proposition = new Proposition(s);
                sc = Score.calculerScore(hist, proposition);
                if(sc.c > max && !hist.contains(proposition)){
                    max = sc.c;
                    meilleur = proposition;
                    meilleur.b = sc.b;
                    meilleur.m = sc.m;
                    System.out.println("**********************************************************");
                    System.out.println("YAW RAHO DKHEL");
                    System.out.println("**********************************************************");
                }

//                System.out.println("Proposition :-> " + proposition.toString() + " avec " + sc.c + " " + sc.b + " " + sc.m);

            }
            i++;
            System.out.println("Meilleur :-> " + meilleur.toString() + " avec " + max);
            System.out.println("Historique :-> " + hist.history.size() + " " + hist.history.toString());
            System.out.println("*****************************************************************************************");

        }

        System.out.println(meilleur.toString());
    }

}
