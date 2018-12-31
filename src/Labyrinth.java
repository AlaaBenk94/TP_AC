import javax.swing.*;
import java.util.ArrayList;

public class Labyrinth {

    private Algorithme algo;
    private Graph graph;
    private ArrayList<Edge> arbreCouvrant;
    private int start = 0;
    private int end;

    public Labyrinth(Algorithme algo, int dim){
        this.algo = algo;
        end = dim*dim-1;
        creerLabyrinth(dim);
    }

    public void creerLabyrinth(int D){
        graph = Graph.Grid(D);
        arbreCouvrant = algo.getArbreCouvrante(graph);
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



    public void setAlgo(Algorithme algo) {
        this.algo = algo;
    }

    public Algorithme getAlgo() {
        return algo;
    }

    public void ShowMe(){
        Display disp = new Display();
        Display disp2 = new Display();

        disp.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        disp2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        disp.setImage(graph.toImage(-1,null));
        disp2.setImage(graph.toLabyrinth());
    }

    public static void main(String[] args){
        Labyrinth laby = new Labyrinth(new AldousBroder(), 6);
        laby.ShowMe();
        System.out.println("Nb Culs de Sac :> " + laby.getNombreCulDeSac());
        System.out.println("Distance Start2End :> " + laby.getDistance());
    }

}
