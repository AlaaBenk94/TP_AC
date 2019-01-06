package deuxieme_partie;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CombinaisonsSecretes {

    public ArrayList<Proposition> combinaisonsSecretes;

    public CombinaisonsSecretes(){
        combinaisonsSecretes = new ArrayList<>();
    }

    public static CombinaisonsSecretes genererCombinaisonsSecretes(int k, int n, Evaluation eval){

        CombinaisonsSecretes ret = new CombinaisonsSecretes();

        try {

            BufferedReader br = new BufferedReader(new FileReader("/home/alaabenk/Desktop/cas.csv"));
            String s;

            while((s = br.readLine()) != null){
                s = s.replace(",","");
                Proposition p = new Proposition(s);
                eval.evaluer(p);
                ret.combinaisonsSecretes.add(p);

                System.out.println(p.toString());
            }

            System.out.println(ret.combinaisonsSecretes.size());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ret;
    }


    public int nbCompatible(int b, int m){
        int ret = 0;

        for(Proposition p : combinaisonsSecretes){
            if(p.b == b && p.m == m)
                ret++;
        }

        return ret;
    }

}
