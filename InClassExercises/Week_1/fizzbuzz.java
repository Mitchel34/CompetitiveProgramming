import java.util.Scanner;

public class fizzbuzz {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int X = scan.nextInt();
        int Y = scan.nextInt();
        int N = scan.nextInt();

        for (int num = 1; num <= N; num++){
            if(num % X == 0 && num % Y == 0) System.out.println("FizzBuzz");
            else if (num % X == 0) System.out.println("Fizz");
            else if (num % Y == 0) System.out.println("Buzz");
            else System.out.println(num);
        }
    }
}
