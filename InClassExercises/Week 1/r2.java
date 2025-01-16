import java.util.Scanner;

public class r2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int r1 = scan.nextInt();
        int S = scan.nextInt();
        int r2 = (2 * S) - r1;

        System.out.print(r2);
    }
}