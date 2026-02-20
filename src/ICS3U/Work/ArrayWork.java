package ICS3U.Work;

public class ArrayWork {
    public static void main(String[] args) {
        int[] nums = {1, 3, 7, 2, 6};
        int[] arr = {2, 6, 1, 8};

        int largest = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > largest) {
                largest = nums[i];
            }
        }

        System.out.println(largest);

        int largest2 = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > largest2) {
                largest2 = arr[i];
            }
        }

        System.out.println(largest2);
    }
}
