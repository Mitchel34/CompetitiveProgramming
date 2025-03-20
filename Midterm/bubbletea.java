import java.util.*;

public class bubbletea {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int a = scan.nextInt();
        int[] teaPrices = new int[a];
        for (int i = 0; i < a; i++) {
            teaPrices[i] = scan.nextInt();
        }

        int b = scan.nextInt();
        int[] topPrices = new int[b];
        for (int i = 0; i < b; i++) {
            topPrices[i] = scan.nextInt();
        }

        int[][] okTop = new int[a][];
        for (int i = 0; i < a; i++) {
            int c = scan.nextInt();
            okTop[i] = new int[c];
            for (int j = 0; j < c; j++) {
                okTop[i][j] = scan.nextInt() - 1;
            }
        }

        int money = scan.nextInt();

        int minCost = Integer.MAX_VALUE;
        for (int i = 0; i < a; i++) {
            for (int topNum : okTop[i]) {
                int cost = teaPrices[i] + topPrices[topNum];
                minCost = Math.min(minCost, cost);
            }
        }
        if (minCost > money) {
            System.out.println("0");
        } else {
            System.out.println(money / minCost - 1);
        }
        scan.close();
    }
}