public class main {

    public static void main(String[] args) {
        Statistique stat;
        Graph g=Graph.example();
        Algorithme algo;

        System.out.println("////////////////////////////// Analyse d'Algo de Kruskal //////////////////////////");

        algo =new Kruskal();

        stat =Statistique.Lancer(algo,g,1000000);
        stat.affichage();

        System.out.println();

        System.out.println("////////////////////////////// Analyse d'Algo de Wilson //////////////////////////");

        algo = new Wilson();

        stat = Statistique.Lancer(algo, g,1000000);
        stat.affichage();

        System.out.println();


    }
}
