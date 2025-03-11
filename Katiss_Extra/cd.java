import java.io.*;
import java.util.*;

public class cd {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        while (true) {
            String[] firstLine = br.readLine().split(" ");
            int n = Integer.parseInt(firstLine[0]);
            int m = Integer.parseInt(firstLine[1]);

            if (n == 0 && m == 0) break; // End of input

            // Read Jack's CDs
            Set<Integer> jackCDs = new HashSet<>();
            for (int i = 0; i < n; i++) {
                jackCDs.add(Integer.parseInt(br.readLine()));
            }

            // Read Jill's CDs and count common ones
            int commonCount = 0;
            for (int j = 0; j < m; j++) {
                int cd = Integer.parseInt(br.readLine());
                if (jackCDs.contains(cd)) {
                    commonCount++;
                }
            }

            // Output the result for this test case
            out.println(commonCount);
        }

        out.flush();
        out.close();
    }
}
