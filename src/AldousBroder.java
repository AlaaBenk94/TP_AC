import java.util.ArrayList;
import java.util.Random;

public class AldousBroder implements Algorihtme {

    private Random rand;
    private int nbSommetNonVisite;
    private boolean[] sommetsVisites;
    private int sommetCurrent;
    private ArrayList<Edge> arbreCouvrant;

    public AldousBroder(){
        rand = new Random();
        nbSommetNonVisite = 0;
        arbreCouvrant = new ArrayList<>();
    }

    @Override
    public ArrayList<Edge> getArbreCouvrante(Graph graph) {

        initialisation(graph.vertices());

        while(nbSommetNonVisite > 0) {
            graph.adj(sommetCurrent);
            sommetSuivant(graph);
        }

        return arbreCouvrant;
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
        }
    }



}
