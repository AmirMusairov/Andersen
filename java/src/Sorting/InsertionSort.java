package Sorting;

public class InsertionSort {

    public static void main(String args[]) {
        int arr[] = {100, 28, 19, 32, 6, 77};
        insertionSort(arr);
    }

    public static int[] insertionSort(int arr[]) {
        for (int i = 1; i < arr.length; i++) {
            int valueToSort = arr[i];
            int j;
            for (j = i; j > 0 && arr[j - 1] > valueToSort; j--) {
                arr[j] = arr[j - 1];
            }

            arr[j] = valueToSort;
            System.out.print("Iteration " + (i) + ": ");
            printArray(arr);
        }

        return arr;
    }

    public static void printArray(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
