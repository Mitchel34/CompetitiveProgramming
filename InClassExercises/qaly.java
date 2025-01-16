import java.util.Scanner;
public class qaly {
        public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        double periodsOfLife = scan.nextDouble();
        double qaly = 0;

        for (int i = 0; i < periodsOfLife; i ++)
        {
            double qoly = scan.nextDouble();
            double period = scan.nextDouble();

            qaly += (qoly*period);
        }
        System.out.print(qaly);
    }
}
