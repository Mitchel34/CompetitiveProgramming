import java.util.Scanner;
public class cetiri {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int[] nums = new int[3];
        for (int i = 0; i < 3; i++) {
            nums[i] = scan.nextInt();
        }

        // sort the array   
        for (int i = 0; i < 3; i++) {
            for (int j = i + 1; j < 3; j++) {
                if (nums[i] > nums[j]) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }
        //if the gap between the first and second number is the same
        //as the gap between the second and third number
        if (nums[1] - nums[0] == nums[2] - nums[1]) {
            System.out.println(nums[2] + (nums[2] - nums[1]));
        } else if (nums[1] - nums[0] > nums[2] - nums[1]) {
            System.out.println(nums[0] + (nums[2] - nums[1]));
        } else {
            System.out.println(nums[1] + (nums[1] - nums[0]));
        }
    }
}