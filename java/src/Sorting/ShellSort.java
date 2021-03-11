package Sorting;

import java.util.Arrays;

public class ShellSort {

    public static void main(String[] args) {
        int[] array = {25, 51, 30, 12, 77, 66, 888, 121, 39, 973};
        System.out.println("Before Sorting : ");
        System.out.println(Arrays.toString(array));
        System.out.println("===================");
        System.out.println("After Sorting : ");
        array = shellSort(array);
        System.out.println(Arrays.toString(array));
    }

    private static int[] shellSort(int[] array) {
        int h = 1;
        while (h <= array.length / 3) {
            h = 3 * h + 1;
        }

        while (h > 0) {
            for (int i = 0; i < array.length; i++) {

                int temp = array[i];
                int j;

                for (j = i; j > h - 1 && array[j - h] >= temp; j = j - h) {
                    array[j] = array[j - h];
                }
                array[j] = temp;
            }
            h = (h - 1) / 3;
        }
        return array;
    }
}
