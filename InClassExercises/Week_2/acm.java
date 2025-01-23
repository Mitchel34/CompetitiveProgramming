import java.util.Scanner;
public class acm {
    public static void main(String[] args) {

            Scanner scan = new Scanner (System.in);
    
            //delimiter to get the minutes and the problem
            //scan.useDelimiter(":|\\s+");
            int[] solved = new int[26];

            int numMinutes = scan.nextInt();
            int time = 0;
            int numSovled = 0;
    
            while (numMinutes != -1) {
                
                String problemName = scan.next();
                problem prob1 = new problem();
                prob1.name = problemName;
                prob1.time = numMinutes;
                prob1.solved = false;

                String result = scan.next();
    
                if (result.equals("right")) {
                    numSovled++;
                    prob1.time += numMinutes;
                    time += prob1.time;
                } else {
                    prob1.time += 20;
                }
                numMinutes = scan.nextInt();
            }
            System.out.println(numSovled + " " + time);
    }

    static class problem  {
        String name;
        boolean solved;
        int time;
    }
}