package deuxieme_partie;


public class Algorithme_Combinaisons {
    int Nombre_Combinaisons(int K,int N,int B,int M){
        int[][]C=new int[N+1][N+1];
        for (int b=0 ;b<N+1;b++)
            for (int m=0;m<N+1;m++)
                C[b][m]=0;
        C[0][0]=1;
        int k=K-N;
        for(int n=1;n<N+1;n++){
            k++;
            int[][]C1=new int[N+1][N+1];
            for (int b=0 ;b<N+1;b++)
                for (int m=0;m<N+1;m++) {
                    int bienplacer = 0;
                    if((b-1)>=0)bienplacer=C[b-1][m];
                    int malplacer = 0;
                    if((m-1)>=0)malplacer=C[b][m-1];
                    C1[b][m]=bienplacer+(malplacer*(n-b))+(C[b][m]*(k-n));
                }
            C=C1;
        }

        return C[B][M];

    }
    public static void main(String[] args){
        Algorithme_Combinaisons algo=new Algorithme_Combinaisons();
        System.out.print(algo.Nombre_Combinaisons(8,4,2,1));
    }


}
