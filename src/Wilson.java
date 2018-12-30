import java.util.ArrayList;
import java.util.Random;

public class Wilson implements Algorithme {
    private boolean[] visiter;
    @Override
    public ArrayList<Edge> getArbreCouvrante(Graph graph) {
        if(graph==null)throw new IllegalArgumentException();
        visiter=new boolean[graph.vertices()];
        visiter[0]=true;
        ArrayList<Edge>arbre=new ArrayList<>();
        while (!tout_visiter()){
            int suiv=choix_non_visiter();
            ArrayList<Edge> chemin=marche(suiv,graph);
           // System.out.println(suiv);
          // for (Edge e:chemin)System.out.print(e.from+"--"+e.to+" ");System.out.println();
            chemin=Eliminer_cycle(chemin,suiv);
          // for (Edge e:chemin)System.out.print(e.from+"--"+e.to+" ");System.out.println();
            visiter_chemin(chemin);
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
            //System.out.print(sommet+"   ");
            //for(Edge arc :adj)System.out.print(arc.from+"--"+arc.to+" ");System.out.println();
            Edge suiv = adj.get(random.nextInt(adj.size()));
            chemin.add(suiv);
            sommet=get_adj(sommet,suiv);
        }
        chemin=suprimer_rep(chemin);
        return chemin;
    }

    int get_adj(int sommet ,Edge arc ){
        if(sommet==arc.from)return arc.to;
        else return arc.from;
    }

    void visiter_chemin(ArrayList<Edge> chemin){
        for (Edge arc :chemin)visiter[arc.from]=visiter[arc.to]=true;
    }

    void ajouter_chemin(ArrayList<Edge>arbre,ArrayList<Edge> chemin){
        for (Edge arc :chemin)arbre.add(arc);
    }

    ArrayList<Edge>Eliminer_cycle(ArrayList<Edge> chemin,int depar){
       ArrayList<Edge> chemin_sans_cycle=new ArrayList<Edge>();
       int i=0,sommet=depar,lastocur=0;

       while (i<chemin.size()){

           Edge arc=chemin.get(i);

           lastocur=i;
           for(int j=i+1;j<chemin.size();j++) {
               if(sommet==chemin.get(j).from||sommet==chemin.get(j).to)lastocur=j;
           }

           if(lastocur==i){
               chemin_sans_cycle.add(arc);
               sommet=get_adj(sommet,arc);
           }else{
              i=lastocur;
              sommet=get_adj(sommet,chemin.get(i));
              if(i==chemin.size()-1){
                  chemin_sans_cycle.add(chemin.get(i));
              }
           }

           i++;
       }

      // System.out.print("entrer "+depar+"  ");aff(chemin);
      // System.out.print("sortie   ");aff(chemin_sans_cycle);
       return chemin_sans_cycle;
    }

    ArrayList<Edge>suprimer_rep(ArrayList<Edge> chemin){
        ArrayList<Edge> chemin_sans_rep=new ArrayList<>();
        Edge pred=chemin.get(0);
        chemin_sans_rep.add(pred);
        for(Edge elem:chemin)
            if(!elem.egale(pred)){
                chemin_sans_rep.add(elem);
                pred=elem;
            }
        return chemin_sans_rep;
    }

    void aff(ArrayList<Edge> chemin){
        for (Edge e:chemin){
            System.out.print(e.from+"--"+e.to+" ");
        }
        System.out.println();
    }

}
