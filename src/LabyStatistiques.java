public class LabyStatistiques {

    private int MoyNbCulSac;
    private double MoyDistance;
    private int nbCycle;


    public LabyStatistiques(int nbCycle){
        MoyDistance = 0;
        MoyNbCulSac = 0;
        this.nbCycle = nbCycle;
    }

    public void setMoyDistance(double moyDistance) {
        MoyDistance = moyDistance;
    }

    public void setMoyNbCulSac(int moyNbCulSac) {
        MoyNbCulSac = moyNbCulSac;
    }

    public double getMoyDistance() {
        return MoyDistance;
    }

    public int getMoyNbCulSac() {
        return MoyNbCulSac;
    }

    public int getNbCycle() {
        return nbCycle;
    }

    public void printStat(){
        System.out.println("********************************************************");
        System.out.println("Nombre d'execution : " + this.nbCycle);
        System.out.println("Nombre Moyen des Culs de Sac : " + this.MoyNbCulSac);
        System.out.println("Distance E/S Moyenne : " + this.MoyDistance);
        System.out.println("********************************************************");
    }

    public static LabyStatistiques lancer(int n, Labyrinth laby) {
        if(laby == null)
            throw new IllegalArgumentException();

        LabyStatistiques stat = new LabyStatistiques(n);

        int nbCulSac = 0;
        int nbDistance = 0;

        for (int i=1 ; i<=n ; i++){
            nbCulSac += laby.getNombreCulDeSac();
            nbDistance += laby.getDistance();
            laby.reset();
            laby.construireChemin();
        }

        stat.setMoyDistance((double) nbDistance/n);
        stat.setMoyNbCulSac(nbCulSac/n);

        return stat;
    }


    /**
     * Pour les tests
     */
    public static void main(String[] args){

        LabyStatistiques stat = LabyStatistiques.lancer(1000, Labyrinth.creerLabyrinth(20, new AldousBroder()));
        stat.printStat();
        stat = LabyStatistiques.lancer(1000, Labyrinth.creerLabyrinth(20, new Kruskal()));
        stat.printStat();
    }


}
