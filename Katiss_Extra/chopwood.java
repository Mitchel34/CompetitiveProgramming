import java.util.*;
import java.io.*;
public class chopwood {
     public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        
        int n = Integer.parseInt(br.readLine());
        // v[i] will hold the neighbor for the i-th removed leaf
        int[] v = new int[n];
        // Read the v-column
        for (int i = 0; i < n; i++) {
            v[i] = Integer.parseInt(br.readLine());
        }
        
        // There are n+1 vertices labeled 1..n+1.
        // count[x] = how many times x appears in v
        int[] count = new int[n+2];  // a little bigger for safety
        for (int i = 0; i < n; i++) {
            count[v[i]]++;
        }
        
        // A min-heap of all vertices that currently look like leaves (count[x] == 0).
        PriorityQueue<Integer> leaves = new PriorityQueue<>();
        for (int x = 1; x <= n+1; x++) {
            if (count[x] == 0) {
                leaves.add(x);
            }
        }
        
        // We will store the "u-column" (the actual leaves removed in order).
        int[] u = new int[n];
        
        // We also need to build adjacency for the final consistency check.
        // adjacency[x] = list of neighbors of x in the final tree we construct.
        List<Integer>[] adj = new ArrayList[n+2];
        for (int x = 0; x <= n+1; x++) {
            adj[x] = new ArrayList<>();
        }
        
        // Keep track of which vertices we have "used up" as leaves
        boolean[] used = new boolean[n+2];
        
        // Main loop: for each edge in the order they were removed
        for (int i = 0; i < n; i++) {
            if (leaves.isEmpty()) {
                // No available leaf -> not reconstructable
                out.println("Error");
                out.flush();
                return;
            }
            int leaf = leaves.poll();
            used[leaf] = true;  // This leaf is now removed from the tree
            
            int neighbor = v[i];
            // Record edge (leaf, neighbor)
            adj[leaf].add(neighbor);
            adj[neighbor].add(leaf);
            
            // Decrement count of that neighbor
            count[neighbor]--;
            if (count[neighbor] < 0) {
                // Means we used 'neighbor' more times than it appeared in v
                out.println("Error");
                out.flush();
                return;
            }
            // If neighbor is now finished appearing, it may become a leaf
            if (count[neighbor] == 0 && !used[neighbor]) {
                leaves.add(neighbor);
            }
            u[i] = leaf;  // record the leaf
        }
        
        // At this point we've built a candidate tree with edges (u[i], v[i]).
        // We must check that it really does yield the same v-column
        // if we do the "remove the smallest leaf" procedure on *that* graph.
        
        // First compute degrees
        int[] deg = new int[n+2];
        for (int x = 1; x <= n+1; x++) {
            deg[x] = adj[x].size();
        }
        
        // The standard "peel off leaves" simulation
        PriorityQueue<Integer> checkLeaves = new PriorityQueue<>();
        for (int x = 1; x <= n+1; x++) {
            if (deg[x] == 1) {
                checkLeaves.add(x);
            }
        }
        
        for (int i = 0; i < n; i++) {
            if (checkLeaves.isEmpty()) {
                out.println("Error");
                out.flush();
                return;
            }
            // The actual smallest leaf
            int actualLeaf = checkLeaves.poll();
            if (actualLeaf != u[i]) {
                // The leaf we remove in the real tree does not match
                // the leaf we claimed we'd remove at step i
                out.println("Error");
                out.flush();
                return;
            }
            // Exactly one neighbor, find it
            // adjacency[actualLeaf] has exactly 1 neighbor if deg is correct
            int neighbor = -1;
            for (int nb : adj[actualLeaf]) {
                if (deg[nb] > 0) {
                    neighbor = nb;
                    break;
                }
            }
            if (neighbor == -1) {
                // no neighbor found -> mismatch
                out.println("Error");
                out.flush();
                return;
            }
            // This neighbor must match v[i]
            if (neighbor != v[i]) {
                out.println("Error");
                out.flush();
                return;
            }
            // "Remove" this leaf from the graph
            deg[actualLeaf] = 0;
            deg[neighbor]--;
            if (deg[neighbor] == 1) {
                checkLeaves.add(neighbor);
            }
        }
        
        // If we get here, the reconstruction is consistent
        // Output the u-column
        for (int i = 0; i < n; i++) {
            out.println(u[i]);
        }
        out.flush();
    }
}