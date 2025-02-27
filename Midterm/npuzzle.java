import java.util.*;
public class npuzzle {

    // A * search algorithm (Manhanttan distance) similar to my maze solver app
     public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Build the board
        char[][] board = new char[4][4];
        for (int i = 0; i < 4; i++) {
            String line = scan.nextLine();
            for (int j = 0; j < 4; j++) {
                board[i][j] = line.charAt(j);
            }
        }
        scan.close();

        // Scatter to return
        int scatter = 0;

        // Check the board

        for (int i = 0; i < 4; i ++) {
            for (int j = 0; j < 4; j++) {
                char ch = board[i][j];
                // Ignore the empty space
                if (ch == '.') {
                    continue;
                }

                // Turn the letters into indexes
                int index = ch - 'A';
                int goalRow = index / 4;
                int goalCol = index % 4;

                // A* search algorithm
                scatter += Math.abs(i - goalRow) + Math.abs(j - goalCol);

            }
        }

        System.out.println(scatter);
     }
}