package deuxieme_partie;

import javax.swing.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static deuxieme_partie.CombinaisonsSecretes.combinaisonsSecretes;

public class Score {

    public int b;
    public int m;
    public int c;

    public String toString(){
        return "(" + b + "," + m + "," + c + ")";
    }

    public static int[] maxBM(Proposition p, Historique h){
        HashMap<String, ArrayList<String>> list = new HashMap<>();
        for(String s : combinaisonsSecretes){
            if(!s.equals(p.s)){
                int[] k = Evaluation.evaluer(s, p);
                if(list.containsKey(k[0] + ":" + k[1])){
                    list.get(k[0] + ":" + k[1]).add(s);
                } else {
                    ArrayList l = new ArrayList<String>();
                    l.add(s);
                    list.put(k[0] + ":" + k[1],l);
                }
            }
        }

        int[] bm = new int[3];
        bm[2] = 0;
        for(Map.Entry<String, ArrayList<String>> entry : list.entrySet()){
            ArrayList<String> temp = CombinaisonsSecretes.intersection(list.get(entry.getKey()), h.compatibles);
            list.put(entry.getKey(), temp);
            if(bm[2] < temp.size()){
                String[] s = entry.getKey().split(":");
                bm[0] = Integer.parseInt(s[0]);
                bm[1] = Integer.parseInt(s[1]);
                bm[2] = temp.size();
            }
        }


        return bm;

    }

    public static Score calculerScore(Historique hist, Proposition prop){
        Score score = new Score();
        int[] sc = Score.maxBM(prop, hist);

        score.b = sc[0];
        score.m = sc[1];
        score.c = sc[2];

        return score;
    }

}
