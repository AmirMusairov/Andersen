package Sorting;

public class MergeSort {

    static int arr[] = {101, 22, 11, 33, 5, 77, 44};

    public static void main(String args[]) {
        System.out.println("Before sorting: ");
        printArray(arr, 0, arr.length - 1);
        System.out.println("=======================");

        mergeSort(0, arr.length - 1);

        System.out.println("========================");

        System.out.println("After sorting: ");
        printArray(arr, 0, arr.length - 1);
    }

    public static void mergeSort(int start, int end) {
        int mid = (start + end) / 2;
        if (start < end) {
            //left half
            mergeSort(start, mid);
            // right half
            mergeSort(mid + 1, end);
            // left and right half
            merge(start, mid, end);
        }
    }

    private static void merge(int start, int mid, int end) {
        int[] tempArray = new int[arr.length];
        int tempArrayIndex = start;

        System.out.print("Before Merging:  ");
        printArray(arr, start, end);

        int startIndex = start;
        int midIndex = mid + 1;

        while (startIndex <= mid && midIndex <= end) {
            if (arr[startIndex] < arr[midIndex]) {
                tempArray[tempArrayIndex++] = arr[startIndex++];
            } else {
                tempArray[tempArrayIndex++] = arr[midIndex++];
            }
        }

        while (startIndex <= mid) {
            tempArray[tempArrayIndex++] = arr[startIndex++];
        }
        while (midIndex <= end) {
            tempArray[tempArrayIndex++] = arr[midIndex++];
        }

        for (int i = start; i <= end; i++) {
            arr[i] = tempArray[i];
        }

        System.out.print("After merging:   ");
        printArray(tempArray, start, end);
        System.out.println();
    }

    public static void printArray(int arr[], int start, int end) {
        for (int i = start; i <= end; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
