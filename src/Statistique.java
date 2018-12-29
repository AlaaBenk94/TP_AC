import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Statistique {
    private int nblancement;
    private Map<ArrayList<Edge>,Integer> arbre_ocuronce;
    private Map<ArrayList<Edge>,Float> arbre_poba;

    private Statistique(){
        arbre_ocuronce=new HashMap<>();
        arbre_poba=new HashMap<>();
    };

    public static Statistique Lancer(Algorihtme algo,Graph graph,int nbrfois){
        if(algo==null || graph==null) throw new IllegalArgumentException();

        Statistique statistique=new Statistique();
        statistique.setNblancement(nbrfois);

        for (int i=0;i<nbrfois;i++){
            Graph graph_clone=graph.clone();
            ArrayList<Edge> echantillon=algo.getArbreCouvrante(graph_clone);
            statistique.add_echantillon(echantillon);
        }

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
            float p=nb_oc/nblancement;
            arbre_poba.put(arbre,p);
        }

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

    private void affichage(){

        System.out.println( "les résultat de lancement d'algo "+ nblancement+ " fois sur le meme graphe ");

        String[] champ={"              arbre             ","   nombre d'occurence   ","   probabilité  "};
        for (String s :champ)System.out.print(s);

        for(Map.Entry<ArrayList<Edge>,Integer> prototype : arbre_ocuronce.entrySet()) {
            ArrayList<Edge> arbre = prototype.getKey();
            int nb_oc = prototype.getValue();

        }


    }

    public int getNblancement() {
        return nblancement;
    }

    public void setNblancement(int nblancement) {
        this.nblancement = nblancement;
    }

    public Map<ArrayList<Edge>, Integer> getArbre_ocuronce() {
        return arbre_ocuronce;
    }

    public void setArbre_ocuronce(Map<ArrayList<Edge>, Integer> arbre_ocuronce) {
        this.arbre_ocuronce = arbre_ocuronce;
    }

    public Map<ArrayList<Edge>, Float> getArbre_poba() {
        return arbre_poba;
    }

    public void setArbre_poba(Map<ArrayList<Edge>, Float> arbre_poba) {
        this.arbre_poba = arbre_poba;
    }

}
