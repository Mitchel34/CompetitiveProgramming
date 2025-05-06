import java.io.*;
import java.util.*;

public class gold {
    static int W, H;
    static char[][] grid;
    static boolean[][] visited;
    // Directions: up, down, left, right
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] wh = br.readLine().split(" ");
        W = Integer.parseInt(wh[0]);
        H = Integer.parseInt(wh[1]);

        grid = new char[H][W];
        visited = new boolean[H][W];
        int startR = -1, startC = -1;

        for (int r = 0; r < H; r++) {
            String line = br.readLine();
            for (int c = 0; c < W; c++) {
                grid[r][c] = line.charAt(c);
                if (grid[r][c] == 'P') {
                    startR = r;
                    startC = c;
                }
            }
        }

        System.out.println(bfsCollectGold(startR, startC));
    }

    /**
     * Perform BFS from the player's start, collecting gold on safe cells.
     * A cell is considered safe to move on if it is not a wall or trap,
     * and we do not expand further from any cell that is adjacent to a trap.
     */
    private static int bfsCollectGold(int sr, int sc) {
        Queue<int[]> queue = new ArrayDeque<>();
        visited[sr][sc] = true;
        queue.offer(new int[]{sr, sc});
        int goldCount = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0], c = cur[1];

            // If current cell has gold, pick it up
            if (grid[r][c] == 'G') {
                goldCount++;
            }

            // If there's a trap adjacent, we "sense a draft" and do not move further from here
            if (isAdjacentToTrap(r, c)) {
                continue;
            }

            // Otherwise, explore neighbors
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (nr < 0 || nr >= H || nc < 0 || nc >= W) continue;
                if (visited[nr][nc]) continue;
                char ch = grid[nr][nc];
                // Can only walk on floor, gold, or starting position
                if (ch == '#' || ch == 'T') continue;
                visited[nr][nc] = true;
                queue.offer(new int[]{nr, nc});
            }
        }

        return goldCount;
    }

    /**
     * Check if any of the four orthogonal neighbors is a trap.
     */
    private static boolean isAdjacentToTrap(int r, int c) {
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr < 0 || nr >= H || nc < 0 || nc >= W) continue;
            if (grid[nr][nc] == 'T') {
                return true;
            }
        }
        return false;
    }
}
