// Every year, Pumpkin Pete comes up with a couple of different Jack-O’-Lantern ideas for his annual Halloween special. He stacks them up on haystacks for everyone to enjoy and take pictures with. To make sure that there’s a wide variety of displays, he wants to make sure many possible Jack-O’-Lanterns designs are available. He has come up with many eye, nose, and mouth designs and would like to know how many unique designs are possible. He needs your help to set up the displays before the special kicks off!

// Input
// The input consists of one line which contains three integers. The first, N
// , indicates the number of eye designs. The second, T
// , indicates the number of nose designs. The third, M
// , indicates the number of mouth designs. All three values are in the range [1, 500]
// .

// Output
// Output a single line containing the number of different possible Jack-O’-Lantern designs.

import java.util.*;

public class jackolanternjuxtaposition {
    public static void main(String[] args) {
        System.out.println("Jack-O-Lantern Juxtaposition");


        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int T = scan.nextInt();
        int M = scan.nextInt();

        System.out.println(N*M*T);
    }
}