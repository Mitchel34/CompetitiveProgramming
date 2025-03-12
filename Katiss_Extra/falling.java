import java.util.*;
import java.io.*;

public class falling {
    /*
     * Mitchel Carson
     * Wed MAR 21 2025
     * Boone, NC
     * 
     * Falling Mugs Problem
     * 
     * 
     * Susan is makiing high-speed videos of falling coffee mugs. when alayzeing the
     * videos she wants to know how big the mugs are, but unfortunately they all go
     * destroyed during filming. susan knows some physics though, she has figured
     * out how far the moved between different video frames. the camera was filmeing
     * at a speed of 70 frames per second, which means that a frame n, counted from
     * when the mug was released, the number of millimeters it has moved is d = n^2.
     * The countingn of the frames starts at 0. Susan thinks a certain mug is D
     * militmeters high, but to verify theis she needs to find two frames between
     * which the mug has moved exactly this disntace,. Can you help her do this?
     * 
     * Input: The input file contains the single positive intefer D <= 200000, the
     * distnace to be measured.
     * 
     * Output: Output two non-negative integers n1 and n2, the numbers of the frames
     * that Susan whould compare. They should fulfill n^2(2) - n^2(1) = D. If no two
     * such integers exist, instead outut "impossible".
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int D = scan.nextInt();
        scan.close();

        final int MAX = 200000;
        boolean found = false;

        for (int n1 = 0; n1 <= MAX; n1++) {
            long val = (long) n1 * n1 + D;
            if (val > (long) MAX * MAX) {
                break;
            }

            int n2 = (int) Math.round(Math.sqrt(val));
            if ((long) n2 * n2 == val) {
                System.out.println(n1 + " " + n2);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("impossible");
        }
    }
}