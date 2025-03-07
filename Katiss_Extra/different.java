import java.util.*;

/*
 * Write a program that computer the difference between non-negative integers
 * Input: Each line of the input consists of a pair of integers. Each integer is between 0 and 10^15 (inclousive). The input is terminated by end of file.
 * 
 * Output: For each pair of integgers in the input, output one line, contining the absolute value of the eir difference.
 * 
 * 
 */
public class different {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        while (scan.hasNextLong()) {
            long a = scan.nextLong();
            long b = scan.nextLong();
            System.out.println(Math.abs(a - b));
        }
    }
}