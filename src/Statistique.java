import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Statistique {
    private int nblancement;
    private double temps_moy;
    private double variance;
    private double ecartype;
    private double probamoy;
    private Map<ArrayList<Edge>,Integer> arbre_ocuronce;
    private Map<ArrayList<Edge>,Float> arbre_poba;

    private Statistique(){
        arbre_ocuronce=new HashMap<>();
        arbre_poba=new HashMap<>();
    };

    public static Statistique Lancer(Algorithme algo, Graph graph, int nbrfois){
        if(algo==null || graph==null) throw new IllegalArgumentException();

        Statistique statistique=new Statistique();
        statistique.nblancement=nbrfois;
        statistique.temps_moy=0;

        for (int i=0;i<nbrfois;i++){
            Graph graph_clone=graph.clone();
            long debut = System.currentTimeMillis();
            ArrayList<Edge> echantillon=algo.getArbreCouvrante(graph_clone);
            statistique.temps_moy+=(System.currentTimeMillis()-debut);
            statistique.add_echantillon(echantillon);
        }
       statistique.temps_moy/=(double)nbrfois;
        statistique.calcule_proba();

        return statistique;
    }

    private void add_echantillon(ArrayList<Edge> arbre){
        for(Map.Entry<ArrayList<Edge>,Integer> prototype : arbre_ocuronce.entrySet()) {
            ArrayList<Edge> arbre_prototype = prototype.getKey();
            if(egale(arbre_prototype,arbre)){
                int nb_oc=arbre_ocuronce.get(arbre_prototype);
                arbre_ocuronce.put(arbre_prototype,nb_oc+1);
                return;
            }
        }
        arbre_ocuronce.put(arbre,1);
    }

    private void calcule_proba(){

        for(Map.Entry<ArrayList<Edge>,Integer> prototype : arbre_ocuronce.entrySet()) {
            ArrayList<Edge> arbre = prototype.getKey();
            int nb_oc=prototype.getValue();
            arbre_poba.put(arbre,nb_oc/(float)nblancement);
        }

        for(Map.Entry<ArrayList<Edge>,Float> proba :arbre_poba.entrySet()){
            probamoy+=proba.getValue();
        }
        probamoy/=arbre_poba.size();

        variance=0;
        for(Map.Entry<ArrayList<Edge>,Float> proba :arbre_poba.entrySet()){
            variance+=(double)Math.round( Math.pow(proba.getValue()-probamoy,2)*10000);
        }
        variance/=arbre_poba.size();

        ecartype=Math.sqrt(variance);
    }

    private boolean egale(ArrayList<Edge> arbre1,ArrayList<Edge> arbre2){
        if(arbre1.size()!=arbre2.size())return false;
        for(Edge arc : arbre1){
            Boolean existe=false;
            for(Edge arc2 :arbre2){
                if(arc.egale(arc2)){
                    existe=true;
                    break;
                }
            }
            if(!existe)return false;
        }
        return true;
    }

    public void affichage(){

        System.out.println( "les résultat de lancement d'algo "+ nblancement+ " fois sur le meme graphe ");
        System.out.println( "temps d'execution moyenne :"+new BigDecimal(temps_moy).toString().substring(0,20));
        System.out.println( "la probabilité moyenne des arbre :"+probamoy);
        System.out.println( "la variance des probabilité des arbre  :"+variance);
        System.out.println( "ecart-type des probabilité des arbre  :"+ecartype);

        String[] champ={"  num  ","              arbre             ","        nombre d'occurence   ","       probabilité  "};
        for (String s :champ)System.out.print(s);System.out.println();

        int num=0;
        for(Map.Entry<ArrayList<Edge>,Integer> prototype : arbre_ocuronce.entrySet()) {
            ArrayList<Edge> arbre = prototype.getKey();
            int nb_oc = prototype.getValue();
            float proba = arbre_poba.get(arbre);
            num++;
            String s=get_affichage(arbre);
            System.out.print("   "+num+")  ");
            for(int i=0;i<(32-s.length())/2;i++)System.out.print(" ");
            System.out.print(s);
            for(int i=0;i<(32-s.length())/2;i++)System.out.print(" ");
            System.out.println("             "+nb_oc+"                  "+proba);
        }


    }

    private String get_affichage(ArrayList<Edge> arbre){
        String s="";
        for(Edge e : arbre )s+=" "+e.from+"--"+e.to+" ";
        return s;
    }




}
