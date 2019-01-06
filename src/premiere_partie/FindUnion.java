package premiere_partie;

public class FindUnion {
    private int[] parent;
    public FindUnion(int taille){

        if(taille<0)throw new IllegalArgumentException();

        parent=new int[taille];
        inistialiser();
    }
    public void inistialiser(){

        for (int i=0;i<parent.length;i++)parent[i]=i;

    }
    public int find(int elem){

        if(elem<0||elem>=parent.length)throw new IllegalArgumentException();

        if(elem==parent[elem])
            return elem;
        else
            return find(parent[elem]);

    }
    public void union(int elem1,int elem2){

        if(elem1<0||elem2<0||elem1>parent.length||elem2>parent.length)throw new IllegalArgumentException();

        int racine_elem1=find(elem1);
        int racine_elem2=find(elem2);
        if( racine_elem1!=racine_elem2)
            parent[racine_elem1]=racine_elem2;
    }
    public void affichage(){
        System.out.println("------------------");
        for(int i=0;i<parent.length;i++){
          System.out.print("  "+i);
        }
        System.out.println();
        for(int i=0;i<parent.length;i++){
            System.out.print("  "+parent[i]);
        }
        System.out.println();
        System.out.println("------------------");
    }

}
