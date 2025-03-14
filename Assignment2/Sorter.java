package Assignment2;

import java.util.ArrayList;
import java.util.Random;

public class Sorter {

    public static int[] generateArray(int n, int r) {return generateArrayHelper(n, r);}
    public static void quickSort(int[] arr, int p, int r) {quickSortHelper(arr, p, r);}
    public static void radixSort(int[] arr) {radixSortHelper(arr);}

    // Generates an array of n integers, with each number selected uniformly from
    // [0, 999999]
    // and each chosen to repeat a random number of times in [1, 2r] (ensuring the
    // total is n).
    private static int[] generateArrayHelper(int n, int r) {
        Random rand = new Random();
        ArrayList<Integer> list = new ArrayList<>();

        while (list.size() < n) {
            int number = rand.nextInt(1_000_000); // number from 0 to 999999
            int maxReps = 2 * r;
            int reps = rand.nextInt(maxReps) + 1; // random number in [1, 2r]
            // Adjust the number of repetitions if exceeding total n.
            if (list.size() + reps > n) {
                reps = n - list.size();
            }
            for (int i = 0; i < reps; i++) {
                list.add(number);
            }
        }

        // Convert ArrayList to int array.
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    // QuickSort with random pivot selection.
    private static void quickSortHelper(int[] arr, int p, int r) {
        if (p < r) {
            int q = partition(arr, p, r);
            quickSortHelper(arr, p, q - 1);
            quickSortHelper(arr, q + 1, r);
        }
    }

    // Partition method for QuickSort using a random pivot.
    public static int partition(int[] arr, int p, int r) {
        Random rand = new Random();
        int pivotIndex = p + rand.nextInt(r - p + 1);
        swap(arr, pivotIndex, r); // Move pivot to end.
        int pivot = arr[r];
        int i = p - 1;
        for (int j = p; j < r; j++) {//compares j to pivot 
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, r);
        return i + 1;
    }

    // Helper method to swap two elements in an array.
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // RadixSort implementation that uses stable Counting Sort for each digit.
    private static void radixSortHelper(int[] arr) {
        int max = getMax(arr);
        // Process each digit, starting from the least significant digit.
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortForDigit(arr, exp);
        }
    }

    // Returns the maximum element in the array.
    private static int getMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    // Stable Counting Sort used by RadixSort to sort according to a specific digit.
    private static void countingSortForDigit(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10]; // For digits 0 through 9.

        // Count occurrences of each digit.
        for (int i = 0; i < n; i++) {
            int digit = (arr[i] / exp) % 10;
            count[digit]++;
        }

        // Transform count array such that count[i] now contains actual positions.
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Build the output array in a stable manner.
        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }

        // Copy the sorted numbers back into original array.
        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }
}
