import java.util.Scanner;

public class flexible {
    /*
     * Golomb Industries is designing their new office building following modern
     * principles that allow for flexible, reconfigurable meeting spaces. Their
     * plans include a very wide rectangular room, with a series of optional
     * parallel partitions.
     * 
     * \includegraphics[width=0.5\textwidth ]{example}
     * Figure 1: An example of a configurable space
     * Figure 1 illustrates such a room having a width of 10 units and three
     * optional partitions located 1 unit, 4 units, and 8 units away from the left
     * wall of the room. The width of the room always measures the distance between
     * the left and right walls, and partitions always run parallel to these walls.
     * We do not concern ourselves with the depth of the room.
     * 
     * For this example, if no partitions are used, a meeting can be held in the
     * original space having width 10. If the company needs a space that is
     * precisely 4 units wide, they can use the portion of the room between the left
     * wall and a partition placed at location 4 (or they could use the portion
     * between the partitions at locations 4 and 8). To provide a space having width
     * 7 they can use the portion of the room between the partitions placed at
     * locations 1 and 8 (omitting the partition at location 4).
     * 
     * Given a particular room design, your job is to determine all feasible widths
     * for a meeting.
     * 
     * Input
     * The first line of the input contains two integers: the overall width
     * of the room, with
     * , and the number
     * of intermediate partitions, such that
     * . Following that is a line with
     * integers, each designating the location
     * of an optional partition, such that
     * . Each partition is at a distinct location and the partitions’ locations will
     * be listed in increasing order.
     * 
     * Output
     * Your program should output a list, from smallest to largest, of each distinct
     * width that can be achieved for a meeting space.
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);


        int width = scan.nextInt();

        int partitions = scan.nextInt();

        int[] partition = new int[partitions];

        for (int i = 0; i < partitions; i++) {
            partition[i] = scan.nextInt();
        }

        int[] widths = new int[partitions + 1];

        for (int i = 0; i < partitions; i++) {
            widths[i] = partition[i];
        }

        widths[partitions] = width;

        int[] output = new int[widths.length * (widths.length - 1) / 2];

        int index = 0;

        for (int i = 0; i < widths.length; i++) {
            for (int j = i + 1; j < widths.length; j++) {
                output[index] = widths[j] - widths[i];
                index++;
            }
        }

        for (int i = 0; i < output.length; i++) {
            if (output[i] != 0) {
                System.out.print(output[i] + " ");
            }
        }

        scan.close();
    }
}