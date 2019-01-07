package deuxieme_partie;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CombinaisonsSecretes {

    public static ArrayList<String> combinaisonsSecretes;

    public CombinaisonsSecretes(){
        combinaisonsSecretes = new ArrayList<>();
    }

    public static CombinaisonsSecretes genererCombinaisonsSecretes(){

        CombinaisonsSecretes ret = new CombinaisonsSecretes();

        try {

            BufferedReader br = new BufferedReader(new FileReader("deuxieme_partie/cas.csv"));
            String s;

            while((s = br.readLine()) != null){
                s = s.replace(",","");
                ret.combinaisonsSecretes.add(s);
            }

            System.out.println(ret.combinaisonsSecretes.size());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ret;
    }


    public static ArrayList<String> nbCompatible(Proposition prop){
        ArrayList<String> ret = new ArrayList<>();
        int[] res;
        for(String p : combinaisonsSecretes){
            res = Evaluation.evaluer(p, prop);
            if(res[0]==prop.b && res[1]==prop.m)
                ret.add(p);
        }

        return ret;
    }

    public static ArrayList<String> intersection(ArrayList<String> list1, ArrayList<String> list2) {
        ArrayList<String> list = new ArrayList<String>();

        for (String t : list1) {
            if(list2.contains(t)) {
                list.add(t);
            }
        }

        return list;
    }


}
