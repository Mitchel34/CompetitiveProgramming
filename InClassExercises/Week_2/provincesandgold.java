import java.util.Scanner;

public class provincesandgold {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // get the number of gold, silver, and copper from the input
        int gold = scan.nextInt();
        int silver = scan.nextInt();
        int copper = scan.nextInt();

        // caluclate the buying power
        int buyingPower = gold * 3 + silver * 2 + copper;

        // determine the best card to buy
        if (buyingPower >= 8) {
            System.out.println("Province or Gold");
        } else if (buyingPower >= 6) {
            System.out.println("Duchy or Gold");
        } else if (buyingPower >= 5) {
            System.out.println("Duchy or Silver");
        } else if (buyingPower >= 3) {
            System.out.println("Estate or Silver");
        } else if (buyingPower >= 2) {
            System.out.println("Estate or Copper");
        } else {
            System.out.println("Copper");
        }
    }
}
