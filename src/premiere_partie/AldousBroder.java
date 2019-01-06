package premiere_partie;

import java.util.ArrayList;
import java.util.Random;

public class AldousBroder extends Simulated implements Algorithme {

    private Random rand;
    private int nbSommetNonVisite;
    private boolean[] sommetsVisites;
    private int sommetCurrent;
    private ArrayList<Edge> arbreCouvrant;
    private Display disp, disp2;

    public AldousBroder(){
        rand = new Random();
        nbSommetNonVisite = 0;
        arbreCouvrant = new ArrayList<>();
    }

    public AldousBroder(Simulation sim){
        super(sim);
        rand = new Random();
        nbSommetNonVisite = 0;
        arbreCouvrant = new ArrayList<>();
    }

    @Override
    public ArrayList<Edge> getArbreCouvrante(Graph graph) {

        initialisation(graph.vertices());
        super.drawAnimation(graph, sommetCurrent, sommetsVisites);

        while(nbSommetNonVisite > 0) {
            sommetSuivant(graph);
            super.drawAnimation(graph, sommetCurrent, sommetsVisites);
        }

        return arbreCouvrant;
    }

    private void initialisation(int N) {
        arbreCouvrant = new ArrayList<>();
        nbSommetNonVisite = N;

        sommetsVisites = new boolean[N];
        for(int i=0; i<N;i++)
            sommetsVisites[i] = false;

        sommetCurrent = rand.nextInt(N);
        sommetsVisites[sommetCurrent] = true;
        nbSommetNonVisite--;
    }

    private void sommetSuivant(Graph graph){
        rand = new Random();
        ArrayList<Edge> voisins = graph.getAdj()[sommetCurrent];
        Edge arreteChoisie = voisins.get(rand.nextInt(voisins.size()));

        sommetCurrent = arreteChoisie.other(sommetCurrent);

        if(!sommetsVisites[sommetCurrent]) {
            arbreCouvrant.add(arreteChoisie);
            sommetsVisites[sommetCurrent] = true;
            nbSommetNonVisite--;
            arreteChoisie.setUsed(true);
        }
    }

    /**
     * Le main pour les tests
     * @param args
     */
    /*public static void main(String[] args) {

        Graph g = Graph.example();
        AldousBroder ab = new AldousBroder();

        for(Edge e : ab.getArbreCouvrante(g)) {
            System.out.println(e.toString());
        }

        System.out.println("FINISHED");
    }*/

}
