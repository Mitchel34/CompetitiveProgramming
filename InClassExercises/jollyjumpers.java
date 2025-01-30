import java.util.Scanner;

public class jollyjumpers {
    public static void main(String[] args) {
        /*
         * 
         * A sequence of
         * integers is called a jolly jumper if the absolute values of the difference
         * between successive elements take on all the values
         * through
         * . For instance,
         * 
         * 1 4 2 3
         * is a jolly jumper, because the absolutes differences are
         * ,
         * , and
         * respectively. The definition implies that any sequence of a single integer is
         * a jolly jumper. You are to write a program to determine whether or not each
         * of a number of sequences is a jolly jumper.
         * 
         * Input
         * Each line of input contains an integer
         * followed by
         * integers representing the sequence. The values in the sequence are at most
         * in absolute value. Input contains at most
         * lines.
         * 
         * Output
         * For each line of input, generate a line of output saying “Jolly” or “Not
         * jolly”.
         */
        Scanner scan = new Scanner(System.in);

        while (scan.hasNext()) {
            int n = scan.nextInt();

            int[] arr = new int[n];

            for (int i = 0; i < n; i++) {
                arr[i] = scan.nextInt();
            }

            boolean[] diff = new boolean[n - 1];

            for (int i = 0; i < n - 1; i++) {
                int d = Math.abs(arr[i] - arr[i + 1]);
                if (d < n && d > 0) {
                    diff[d - 1] = true;
                }
            }

            boolean isJoly = true;

            for (int i = 0; i < n - 1; i++) {
                if (!diff[i]) {
                    isJoly = false;
                    break;
                }
            }

            if (isJoly) {
                System.out.println("Jolly");
            } else {
                System.out.println("Not jolly");
            }

            if (scan.hasNext()) {
                scan.nextLine();
            }
        }
        scan.close();
    }
}