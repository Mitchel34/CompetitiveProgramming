import java.util.*;

public class cartrouble {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        Map<Integer, List<Integer>> adj = new HashMap<>();
        Map<Integer, List<Integer>> rev = new HashMap<>();
        List<Integer> order = new ArrayList<>();

        while (n-- > 0) {
            int id = scan.nextInt(), k = scan.nextInt();
            if (id != 0) order.add(id);
            for (int i = 0; i < k; i++) {
                int to = scan.nextInt();
                adj.computeIfAbsent(id, x -> new ArrayList<>()).add(to);
                rev.computeIfAbsent(to, x -> new ArrayList<>()).add(id);
            }
        }

        Set<Integer> reachable = bfs(0, adj);
        Set<Integer> canReachCircle = bfs(0, rev);

        List<String> output = new ArrayList<>();
        order.forEach(id -> { if (!canReachCircle.contains(id)) output.add("TRAPPED " + id); });
        order.forEach(id -> { if (!reachable.contains(id))     output.add("UNREACHABLE " + id); });

        if (output.isEmpty()) System.out.println("NO PROBLEMS");
        else output.forEach(System.out::println);
        scan.close();
    }

    private static Set<Integer> bfs(int start, Map<Integer, List<Integer>> graph) {
        Set<Integer> seen = new HashSet<>();
        Deque<Integer> queue = new ArrayDeque<>();
        seen.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v : graph.getOrDefault(u, Collections.emptyList())) {
                if (seen.add(v)) {
                    queue.add(v);
                }
            }
        }
        return seen;
    }
}


