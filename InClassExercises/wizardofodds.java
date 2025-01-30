import java.math.BigInteger;
import java.util.Scanner;
public class wizardofodds {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        // BigInteger is used to store large numbers
        BigInteger n = scan.nextBigInteger();
    
        BigInteger k = scan.nextBigInteger();
        
        BigInteger two = new BigInteger("2");

       // error java.lang arithmeticException negative exponent
        if (n.compareTo(two.pow(k.intValue())) <= 0) {
            System.out.println("Your wish is granted!");
        } else {
            System.out.println("You will become a flying monkey!");
        }
        scan.close();
    }
}