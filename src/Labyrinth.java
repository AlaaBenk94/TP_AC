import javax.swing.*;
import java.util.ArrayList;

public class Labyrinth {

    private Graph graph;
    private ArrayList<Edge> arbreCouvrant;
    private Algorithme algo;
    private int start;
    private int end;

    public Labyrinth(int dim, Algorithme algo){
        this.algo = algo;
        start = 0;
        end = dim*dim-1;
        graph = Graph.Grid(dim);
    }

    public static Labyrinth creerLabyrinth(int D, Algorithme algo){
        Labyrinth laby = new Labyrinth(D, algo);
        laby.construireChemin();
        return laby;
    }

    public void construireChemin() {
        arbreCouvrant = algo.getArbreCouvrante(this.graph);
    }

    public void reset() {
        for( int s=0 ; s<graph.vertices() ; s++)
            for(Edge e : graph.getAdj()[s])
                e.used = false;

        arbreCouvrant = null;
    }

    public int getNombreCulDeSac(){
        int count = 0;
        for(int sommet=0; sommet<graph.vertices(); sommet++){
            if(isCulDeSac(sommet) && (sommet!=start) && (sommet!=end))
                count++;
        }
        return count;
    }

    private boolean isCulDeSac(int sommet){
        int count = 0;
        for(Edge e : graph.adj(sommet)) {
            if(e.used)
                count++;
            if(count > 1)
                return false;
        }
        return true;
    }

    public int getDistance(){
        return getDistance(-1, start)-1;
    }

    private int getDistance(int pred, int s){
        ArrayList<Edge> arretes = getUsedArrets(s);


        if(s != start)
            arretes.removeIf(e -> (e.from == pred || e.to == pred));

        for(Edge e : arretes) {
            int ret = getDistance(s, e.other(s));
            if(ret != -1)
                return 1 + ret;
        }

        return s==end?1:-1;
    }

    private ArrayList<Edge> getUsedArrets(int sommet){
        ArrayList<Edge> arretes = new ArrayList<>();
        for (Edge e : graph.adj(sommet))
            if(e.used)
                arretes.add(e);
        return arretes;
    }

    public void ShowMe(){
        Display disp = new Display();
//        Display disp2 = new Display();

        disp.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        disp2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        disp.setImage(graph.toImage(-1,null));
//        disp2.setImage(graph.toLabyrinth());
    }

   /* public static void main(String[] args){
        Labyrinth laby = Labyrinth.creerLabyrinth(2, new AldousBroder());
        laby.ShowMe();
        System.out.println("Nb Culs de Sac :> " + laby.getNombreCulDeSac());
        System.out.println("Distance Start2End :> " + laby.getDistance());
    }*/

}
