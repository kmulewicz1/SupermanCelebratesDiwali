
import java.util.*;

public class Solution {

    static class Position {
        int row;
        int column;

        Position(int row, int column) {
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


    private static void addDataToTab(int [][]tab) {
        for (int i = 0; i < tab[0].length; i++) {
            int u = scanner.nextInt();
            for (int j = 0; j < u; j++) {
                tab[scanner.nextInt() - 1][i]++;
            }
        }
    }

    private static void initUnderSolutionTab(int[][] tab, int I) {
            for (int j = 1; j < I  ; j++) {
                for (int i = 0; i < tab[0].length; i++) {
                tab[j][i] += tab[j-1][i];
            }

        }
    }

    private static int sol(Position position, int [][]tab, int I) {
        int max;
        if (position.row == tab.length) return -1;
        else {
            for (int i = 0; i < tab[position.column].length ; i++) {
                max=findMax(tab,position.row,i,I);

                    int down = tab[position.row-1][i];
                    if(down>max) max=down;

                tab[position.row][i]=tab[position.row][i]+max;
            }
            position.row++;
            return sol(position,tab,I);
        }
    }




    public static void main(String[] args) {
        int tmp = scanner.nextInt();
        int [][]tab = new int[scanner.nextInt()][tmp];
        int I=scanner.nextInt();
        addDataToTab(tab);
        initUnderSolutionTab(tab,I);

        Position position = new Position(I,0);

        int wynik = sol(position,tab,I);

        for (int i = 0; i < tab[tab.length-1].length; i++) {
            if (tab[tab.length-1][i]>wynik) wynik = tab[tab.length-1][i];
        }
        System.out.println(wynik);
    }
}

