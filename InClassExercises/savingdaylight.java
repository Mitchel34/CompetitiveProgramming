import java.util.Scanner;

public class savingdaylight {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        scan.useDelimiter(":|\\s+");

        while (scan.hasNext()) {
            String month = scan.next();
            String day = scan.next();
            String year = scan.next();

            // parsing the sunrise and sunset time
            int shrs = scan.nextInt();
            int smin = scan.nextInt();
            int ehrs = scan.nextInt();
            int emin = scan.nextInt();

            int tmin = (60 * ehrs + emin) - (60 * shrs + smin);
            System.out.printf("%s %s %s %d hours %d minutes \n", month, day, year, (tmin / 60), (tmin % 60));

        }
    }
}
