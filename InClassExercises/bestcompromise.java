import java.util.Scanner;
import java.util.ArrayList;
public class bestcompromise {

    public static void main(String[] args) {


        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();

        while (t-- > 0) {
            int n = scan.nextInt();
            int m = scan.nextInt();

            ArrayList<String> list = new ArrayList<String>();

            for (int i = 0; i < n; i++) {
                list.add(scan.next());
            }

            String best = "";

            for (int i = 0; i < m; i++) {
                int[] count = new int[2];
                for (int j = 0; j < n; j++) {
                    count[list.get(j).charAt(i) - '0']++;
                }
                best += count[0] > count[1] ? '0' : '1';
            }

            System.out.println(best);
        }

        scan.close();

    }
}