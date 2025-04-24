import java.util.Scanner;


public class sibice {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        // Read N (number of matches), W (width), H (height)
        int N = scan.nextInt();
        int W = scan.nextInt();
        int H = scan.nextInt();
        
        // Compute the maximum length that can fit: the diagonal
        double maxLen = Math.sqrt(W * W + H * H);
        
        // For each match, check if it fits
        for (int i = 0; i < N; i++) {
            int matchLen = scan.nextInt();
            if (matchLen <= maxLen) {
                System.out.println("DA");
            } else {
                System.out.println("NE");
            }
        }
        
        scan.close();
    }
}

