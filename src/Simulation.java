import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Simulation {

    private Display disp;
    private ArrayList<BufferedImage> clips;
    private int CurrentFrame;
    private int delay;


    Simulation(){
        CurrentFrame = 0;
        disp = new Display();
        delay = 500;
        clips = new ArrayList<>();
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public void play(){

        for (int i=CurrentFrame; i<clips.size(); i++){
            disp.setImage(clips.get(i));
            delay();
        }
        CurrentFrame = 0;
    }

    private void delay(){
        try {
            Thread.sleep(this.delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void addClip(BufferedImage img){
        clips.add(img);
    }

    /**
     * For Tests
     */
    public static void main(String[] args){

        Simulation sim = new Simulation();
//        AldousBroder ab = new AldousBroder(sim);
//        ab.getArbreCouvrante(Graph.Grid(5));

//        Kruskal k = new Kruskal(sim);
//        k.getArbreCouvrante(Graph.Grid(5));

        Wilson w = new Wilson(sim);
        w.getArbreCouvrante(Graph.Grid(5));

        sim.play();

    }

}
