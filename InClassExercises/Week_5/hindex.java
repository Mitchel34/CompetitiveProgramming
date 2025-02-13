import java.util.*;
public class hindex {

    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt(); // number of papers

        int[] papers = new int[n]; //number of citations in each paper

        for (int i = 0; i < n; i++) {
            papers[i] = scan.nextInt();
        } 
        Arrays.sort(papers); 

        //Divide and Conquer (Binary Search)
        int low = 0;
        int high = n - 1;
        int hIndex = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2; //mid is the index of the middle element
            if (papers[mid] >= n - mid) { //if the number of citations is greater than or equal to the number of papers
                hIndex = n - mid; //hindex is the number of papers
                high = mid - 1; //move to the left
            } else {
                low = mid + 1; //move to the right
            }
        }
       
        System.out.println(hIndex);
        scan.close();
    }
}