import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Kruskal implements Algorithme {
    private boolean animation;
    ArrayList<BufferedImage> animation_imgs;

    @Override
    public ArrayList<Edge> getArbreCouvrante(Graph graph) {

        if(graph==null)throw new IllegalArgumentException();

        ArrayList<Edge> arbre =new ArrayList<>();
        ArrayList<Edge> aretes=melanger(graph.edges());
        FindUnion ver =new FindUnion(graph.vertices());

        for(Edge arete:aretes){
            int sommet1=arete.from;
            int sommet2=arete.to;
            if(ver.find(sommet1)!=ver.find(sommet2)){
                 ver.union(sommet1,sommet2);
                 arbre.add(arete);
                 arete.used=true;
                 if(animation)animation_imgs.add(graph.toImage());
            }
        }


        return arbre;
    }

    private ArrayList<Edge> melanger(ArrayList<Edge> table){
        int degre=table.size()*4;
        ArrayList<Edge> listmelanger= (ArrayList<Edge>) table.clone();
        Random random=new Random();
        int ind1,ind2;
        for(int i=0;i<degre;i++){
            ind1=random.nextInt(listmelanger.size());
            ind2=random.nextInt(listmelanger.size());
            Edge edge=listmelanger.get(ind1);
            listmelanger.set(ind1,listmelanger.get(ind2));
            listmelanger.set(ind2,edge);
        }
        return listmelanger;
    }
    public void AnimationOn(){
        animation=true;
    }
    public void AnimationOff(){
        animation=false;
    }
    public ArrayList<BufferedImage> getAnimation(){
        return animation_imgs;
    }
}
