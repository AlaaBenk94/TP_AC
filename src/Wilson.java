import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Wilson extends Simulated implements Algorithme {

    private boolean[] visiter;

    public Wilson(){
        super();
    }

    public Wilson(Simulation sim) {
        super(sim);
    }

    @Override
    public ArrayList<Edge> getArbreCouvrante(Graph graph) {
        if(graph==null)throw new IllegalArgumentException();
        visiter=new boolean[graph.vertices()];
        visiter[new Random().nextInt(visiter.length)]=true;
        ArrayList<Edge>arbre=new ArrayList<>();
        while (!tout_visiter()){
            int suiv=choix_non_visiter();
            ArrayList<Edge> chemin=marche(suiv,graph);
            chemin=Eliminer_cycle(chemin,suiv);
            visiter_chemin(chemin,graph);
            ajouter_chemin(arbre,chemin);
        }
        return arbre;
    }

    boolean tout_visiter(){
        for(int i=0;i<visiter.length;i++)
            if(!visiter[i])return false;
        return true;
    }

    int choix_non_visiter(){
        Random random =new Random();
        int sommet=random.nextInt(visiter.length);
        while(visiter[sommet]){
            sommet=random.nextInt(visiter.length);
        }
        return sommet;
    }

    ArrayList<Edge>marche(int depar,Graph graph){
        ArrayList<Edge> chemin =new ArrayList<>();
        Random random=new Random();
        int sommet=depar;
        while (!visiter[sommet]) {
            ArrayList<Edge> adj = graph.getAdj()[sommet];
            Edge suiv = adj.get(random.nextInt(adj.size()));
            chemin.add(suiv);
            sommet=get_suiv(sommet,suiv);
        }
        return chemin;
    }

    int get_suiv(int sommet ,Edge arc ){
        if(sommet==arc.from)return arc.to;
        else return arc.from;
    }

    void visiter_chemin(ArrayList<Edge> chemin,Graph graph){
        for (Edge arc :chemin){
            visiter[arc.from]=true;
            visiter[arc.to]=true;
            arc.used=true;
            super.drawAnimation(graph,-1,visiter);
        }
    }

    void ajouter_chemin(ArrayList<Edge>arbre,ArrayList<Edge> chemin){
        for (Edge arc :chemin)arbre.add(arc);
    }

    ArrayList<Edge>Eliminer_cycle(ArrayList<Edge> chemin,int depar){
        ArrayList<Edge> chemin_sans_cycle=new ArrayList<Edge>();
        int i=0,sommet=depar,lastocur,nboc;
        while (i<chemin.size()){
            lastocur=i;nboc=1;
            for(int j=i+1;j<chemin.size();j++) {
                if(sommet==chemin.get(j).from||sommet==chemin.get(j).to){
                    lastocur=j;
                    nboc++;
                }
            }
            if(i==lastocur){
                chemin_sans_cycle.add(chemin.get(i));
            }else {
              if(nboc%4==0)chemin_sans_cycle.add(chemin.get(i));
                chemin_sans_cycle.add(chemin.get(lastocur));
                i=lastocur;
            }
            sommet=get_suiv(sommet,chemin.get(i));
            i++;
        }
        return chemin_sans_cycle;
    }
}
