import java.util.*;
import java.io.*;

public class borg {
    // Offsets for North, South, West, East moves
    private static final int[][] DIRECTIONS = {{-1,0},{1,0},{0,-1},{0,1}};
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();  // Number of test cases
        
        while (T-- > 0) {
            int x = in.nextInt();  // width
            int y = in.nextInt();  // height
            in.nextLine(); // consume rest of line
            
            // Read the maze
            char[][] maze = new char[y][x];
            for (int row = 0; row < y; row++) {
                String line = in.nextLine();
                for (int col = 0; col < x; col++) {
                    maze[row][col] = line.charAt(col);
                }
            }
            
            // Collect start S and aliens A in a list 'points'
            // Each point is (row, col)
            List<int[]> points = new ArrayList<>();
            // Also track location of S separately (but we will put it in points[0])
            int sRow = -1, sCol = -1;
            
            for (int row = 0; row < y; row++) {
                for (int col = 0; col < x; col++) {
                    if (maze[row][col] == 'S') {
                        sRow = row; 
                        sCol = col;
                    }
                }
            }
            // Add S first
            points.add(new int[]{sRow, sCol});
            
            // Now add each alien A
            for (int row = 0; row < y; row++) {
                for (int col = 0; col < x; col++) {
                    if (maze[row][col] == 'A') {
                        points.add(new int[]{row, col});
                    }
                }
            }
            
            // We'll need a distance matrix between these special points
            int n = points.size();
            int[][] dist = new int[n][n];
            
            // For each point in 'points', do a BFS in the grid to find
            // distances to every other cell.  Then pick out distances
            // to the other special points.
            for (int i = 0; i < n; i++) {
                int[] start = points.get(i);
                int[][] bfsDist = bfsDistances(maze, start[0], start[1]);
                
                for (int j = 0; j < n; j++) {
                    if (i == j) {
                        dist[i][j] = 0;
                    } else {
                        int[] other = points.get(j);
                        dist[i][j] = bfsDist[other[0]][other[1]];
                    }
                }
            }
            
            // Now build a complete graph of size n with edge weights dist[i][j].
            // We want the MST of this graph.  Use Prim’s or Kruskal’s.  Here we use Prim’s for simplicity.
            int answer = primMST(dist, n);
            
            // Print the result for this test case
            System.out.println(answer);
        }
        
        in.close();
    }
    
    // BFS to get distances from (sr, sc) to all reachable cells.
    // Returns a 2D array of distances, or -1 for unreachable (shouldn't happen if "everyone is reachable").
    private static int[][] bfsDistances(char[][] maze, int sr, int sc) {
        int rows = maze.length;
        int cols = maze[0].length;
        int[][] dist = new int[rows][cols];
        for (int r = 0; r < rows; r++) {
            Arrays.fill(dist[r], -1);
        }
        dist[sr][sc] = 0;
        
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sr, sc});
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0], c = cur[1];
            for (int[] d : DIRECTIONS) {
                int nr = r + d[0];
                int nc = c + d[1];
                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
                    if (maze[nr][nc] != '#' && dist[nr][nc] == -1) {
                        dist[nr][nc] = dist[r][c] + 1;
                        queue.offer(new int[]{nr, nc});
                    }
                }
            }
        }
        
        return dist;
    }
    
    // Prim’s algorithm to find MST cost of a complete graph with weight matrix dist.
    private static int primMST(int[][] dist, int n) {
        boolean[] used = new boolean[n];
        int[] minEdge = new int[n];  // min cost edge connecting each node to the MST
        Arrays.fill(minEdge, Integer.MAX_VALUE);
        
        // Start from node 0 (the start 'S')
        minEdge[0] = 0;
        int result = 0;
        
        for (int i = 0; i < n; i++) {
            // Pick the next vertex u not in MST with the smallest edge
            int u = -1;
            int best = Integer.MAX_VALUE;
            for (int v = 0; v < n; v++) {
                if (!used[v] && minEdge[v] < best) {
                    best = minEdge[v];
                    u = v;
                }
            }
            
            // Add this edge to the MST
            result += best;
            used[u] = true;
            
            // Update the edges
            for (int w = 0; w < n; w++) {
                if (!used[w] && dist[u][w] < minEdge[w]) {
                    minEdge[w] = dist[u][w];
                }
            }
        }
        
        return result;
    }
}
