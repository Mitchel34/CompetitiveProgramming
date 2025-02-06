import java.util.Scanner;

public class pet {
    /*
     * In the popular show “Dinner for Five”, five contestants compete in preparing
     * culinary delights. Every evening one of them makes dinner and each of other
     * four then grades it on a scale from 1 to 5. The number of points a contestant
     * gets is equal to the sum of grades they got. The winner of the show is of
     * course the contestant that gets the most points.
     * 
     * Write a program that determines the winner and how many points they got.
     * 
     * Input
     * Five lines, each containing 4 integers, the grades a contestant got. The
     * contestants are numbered 1 to 5 in the order in which their grades were
     * given.
     * 
     * Output
     * Output on a single line the winner’s number and their points, separated by a
     * space. The input data will guarantee that the solution is unique.
     */
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        int max = 0;

        int winner = 0;

        for (int i = 1; i <=5; i++) {
            int sum = 0;
            for (int j =0; j<4; j++) {
                sum += scan.nextInt();

                if(sum > max) {
                    max = sum;
                    winner = i;
                }
            }
        }

        System.out.println(winner + " " + max);

    }
}
