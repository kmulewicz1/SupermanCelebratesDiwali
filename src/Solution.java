
import java.util.*;

public class Solution {

    static class Position {
        int row;
        int column;

        public Position(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }

    private static Scanner scanner = new Scanner(System.in);

    private static int findMax(int[][]tab,int row, int column, int I){
        int max = 0;
        for (int i = 0; i < tab[0].length; i++) {
            if(tab[row-I][i]>max&&i!=column)
                max=tab[row-I][i];
        }
        return max;
    }


    private static int[][] initTab(int N, int H) {

        int [][]tab = new int [H][N];
        for (int i = 0; i < H ; i++) {
            for (int j = 0; j < N ; j++) {
                tab[i][j]=0;
            }
        }
        return tab;
    }

    private static void addDataToTab(int [][]tab) {
        for (int i = 0; i < tab[0].length; i++) {
            int u = scanner.nextInt();
            for (int j = 0; j < u; j++) {
                tab[scanner.nextInt() - 1][i]++;
            }
        }
    }

    private static void initUnderSolutionTab(int[][] tab, int [][]solActual, int I) {
        for (int j = 0; j  < tab[0].length ; j++) {
            solActual[0][j] = tab[0][j];
        }

            for (int j = 1; j < I  ; j++) {
                for (int i = 0; i < tab[0].length; i++) {
                solActual[j][i] = solActual[j-1][i] + tab[j][i];
            }

        }
    }

    private static int sol(Position position, int [][]tab, int I, int [][]solActual) {
        int max;
        if (position.row == tab.length) return -1;
        else {
            for (int i = 0; i < tab[position.column].length ; i++) {
                max=findMax(solActual,position.row,i,I);

                    int down = solActual[position.row-1][i];
                    if(down>max) max=down;

                solActual[position.row][i]=tab[position.row][i]+max;
            }
            position.row++;
            return sol(position,tab,I,solActual);
        }
    }




    public static void main(String[] args) {

        int [][]tab = initTab(scanner.nextInt(),scanner.nextInt());
        int I=scanner.nextInt();
        int [][]solActual = initTab(tab[0].length,tab.length);
        addDataToTab(tab);
        initUnderSolutionTab(tab,solActual,I);

        Position position = new Position(I,0);

        int wynik = sol(position,tab,I,solActual);

        for (int i = 0; i < solActual[solActual.length-1].length; i++) {
            if (solActual[solActual.length-1][i]>wynik) wynik = solActual[solActual.length-1][i];
        }
        System.out.println(wynik);


    }
}

