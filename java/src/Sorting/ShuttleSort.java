package Sorting;

import java.util.Arrays;

public class ShuttleSort {

    void shuttleSort(int nums[]) {
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i <= nums.length - 2; i++) {
                if (nums[i] > nums[i + 1]) {
                    int temp = nums[i];
                    nums[i] = nums[i + 1];
                    nums[i + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
            swapped = false;
            for (int i = nums.length - 2; i >= 0; i--) {
                if (nums[i] > nums[i + 1]) {
                    int temp = nums[i];
                    nums[i] = nums[i + 1];
                    nums[i + 1] = temp;
                    swapped = true;
                }
            }
        } while (swapped);
    }

    public static void main(String args[]) {
        ShuttleSort arr = new ShuttleSort();
        int nums[] = {7, 5, 3, 2, 1, 12, 45};
        System.out.println("Before Sorting: ");
        System.out.println(Arrays.toString(nums));
        arr.shuttleSort(nums);
        System.out.println("After Sorting: ");
        System.out.println(Arrays.toString(nums));
    }
}
