package deuxieme_partie;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Score {

    public ArrayList<Proposition> combinaisonsSecretes;
    public int n;
    public int k;



    public Score(int k, int n){
        this.k = k;
        this.n = n;
        combinaisonsSecretes = new ArrayList<>();
    }

    public void genererCombinaisonsSecretes(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("/home/alaabenk/Desktop/cas.csv"));
            String s;
            while((s = br.readLine()) != null){
                s = s.replace(",","");
                combinaisonsSecretes.add(new Proposition(s));
            }

            System.out.println(combinaisonsSecretes.size());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static int calculerScore(Historique hist, Proposition prop){



        return 0;
    }

    public static void main(String[] args){
        (new Score(6, 4)).genererCombinaisonsSecretes();
    }

}
