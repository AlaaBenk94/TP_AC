import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class AldousBroder implements Algorithme {

    private Random rand;
    private int nbSommetNonVisite;
    private boolean[] sommetsVisites;
    private int sommetCurrent;
    private ArrayList<Edge> arbreCouvrant;
    private Display disp;
    private boolean animationOn;

    public AldousBroder(){
        rand = new Random();
        nbSommetNonVisite = 0;
        arbreCouvrant = new ArrayList<>();
        disp = new Display();
        disp.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        animationOn = true;
    }

    @Override
    public ArrayList<Edge> getArbreCouvrante(Graph graph) {

        initialisation(graph.vertices());

        drawAnimation(graph);

        while(nbSommetNonVisite > 0) {
            sommetSuivant(graph);
            drawAnimation(graph);
        }

        return arbreCouvrant;
    }

    private void drawAnimation(Graph graph){
        if(!animationOn)
            return;

        waitForAnimation();

        disp.setImage(graph.toImage(sommetCurrent, sommetsVisites));

    }

    private void waitForAnimation() {

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void initialisation(int N) {
        nbSommetNonVisite = N;

        sommetsVisites = new boolean[N];
        for(int i=0; i<N;i++)
            sommetsVisites[i] = false;

        sommetCurrent = rand.nextInt(N);
        sommetsVisites[sommetCurrent] = true;
        nbSommetNonVisite--;
    }

    private void sommetSuivant(Graph graph){

        ArrayList<Edge> voisins = graph.adj(sommetCurrent);
        Edge arreteChoisie = voisins.get(rand.nextInt(voisins.size()));

        sommetCurrent = arreteChoisie.other(sommetCurrent);

        if(!sommetsVisites[sommetCurrent]) {
            arbreCouvrant.add(arreteChoisie);
            sommetsVisites[sommetCurrent] = true;
            nbSommetNonVisite--;
            arreteChoisie.setUsed(true);
            graph.addEdge(arreteChoisie);
        }
    }

    /**
     * Le main pour les tests
     * @param args
     */
    public static void main(String[] args) {

        Graph g = Graph.example();
        AldousBroder ab = new AldousBroder();

        ab.getArbreCouvrante(g);

        System.out.println("FINISHED");
    }

}
