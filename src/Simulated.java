public abstract class Simulated {

    protected  boolean animationON;
    protected Simulation sim;

    Simulated(){
        sim = null;
        animationON = false;
    }

    Simulated(Simulation sim){
        this.sim = sim;
        animationON = true;
    }

    protected void drawAnimation(Graph g, int s, boolean[] visited){
        if (!animationON)
            return;
        sim.addClip(g.toImage(s, visited));
    }
}
