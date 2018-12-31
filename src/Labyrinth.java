import java.util.ArrayList;

public class Labyrinth {

    private Algorithme algo;
    private Graph graph;

    public Labyrinth(){

    }

    public void creerLabyrinth(int D){
        graph = Graph.Grid(D);
        algo.getArbreCouvrante(graph);
    }



    public void setAlgo(Algorithme algo) {
        this.algo = algo;
    }

    public Algorithme getAlgo() {
        return algo;
    }

}
