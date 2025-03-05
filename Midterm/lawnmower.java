import java.util.*;

public class lawnmower {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        
        while (true) {
            int nx = scan.nextInt();
            int ny = scan.nextInt();
            double width = scan.nextDouble();

        
            if (nx == 0 && ny == 0 && width == 0) {
                break;
            }
            
            double[] xCut = new double[nx];
            for (int i = 0; i < nx; i++) {
                xCut[i] = scan.nextDouble();
            }

            double[] yCut = new double[ny];
            for (int i = 0; i < ny; i++) {
                yCut[i] = scan.nextDouble();
            }
    
            if (isMowed(xCut, width, 75) && isMowed(yCut, width, 100)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }

        }

    }

    private static boolean isMowed(double[] cuts, double width, double length) {
        Arrays.sort(cuts);
        double current = 0;
        for (double cut : cuts) {
            if (cut - width / 2 > current) {
                return false;
            }
            current = Math.max(current, cut + width / 2);
        }
        return current >= length;
    }
}
