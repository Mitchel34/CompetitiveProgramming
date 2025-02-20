import java.util.*;

public class veci {

    public static void main(String[] args) {
        // given the value n find the smallest number larger than n consisting of the
        // same digits as n
        //1 <= x <= 999999
        //Step 1: Read the input
        //Step 2: Get the digits of the input
        //Step 3: Find the smallest number larger than x consisting of the same digits as x
        //Step 4: Print the result

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // read x from input
        int[] digits = getDigits(n); // get the digits of x
       
        int result = 0;

        // smallest number larger than x
        for (int i = n + 1; i <= 999999; i++) {
            int[] newDigits = getDigits(i);
            if (Arrays.equals(digits, newDigits)) {
                result = i;
                break;
            }
        }
        System.out.println(result);
    }

    public static int[] getDigits(int X) {
        int[] digits = new int[6];
        int i = 0;
        // keep track of the number of zeros in the array
        int zerosCount = 0;
        while (X > 0) {
            digits[i] = X % 10;
            if (digits[i] == 0) {
                zerosCount++;
            }
            X = X / 10;
            i++;
        }
        Arrays.sort(digits);
        
        // start at index zeroes and remove the zeros
        for (int zC = zerosCount; zC < digits.length; zC++) {
            if (digits[zC] == 0) {
                digits[zC] = -1;
            }
        }
        // count the numeber of -1's in the array
        int count = 0;
        for (int j = 0; j < digits.length; j++) {
            if (digits[j] == -1) {
                count++;
            }
        }
        // create a new array with the size of the original array minus the number of -1's
        int[] newDigits = new int[digits.length - count];
        int j = 0;  
        for (int k = 0; k < digits.length; k++) {
            if (digits[k] != -1) {
                newDigits[j] = digits[k];
                j++;
            }
        }
        Arrays.sort(newDigits);
        return newDigits;
    }
}