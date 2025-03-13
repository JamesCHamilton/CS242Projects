package Assignment2;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the size of the input (n): ");
        int n = scanner.nextInt();
        System.out.print("Enter the expected number of repetitions (r): ");
        int r = scanner.nextInt();

        // Generate an array of n elements with the desired repetition properties.
        int[] array1 = Sorter.generateArray(n, r);
        // Create a copy for the second sorting method so both have identical input.
        int[] array2 = Arrays.copyOf(array1, array1.length);

        // Measure QuickSort running time.
        int[] quickSortArray = Arrays.copyOf(array1, array1.length);
        long startQuick = System.nanoTime();
        Sorter.quickSort(quickSortArray, 0, quickSortArray.length - 1);
        long endQuick = System.nanoTime();
        long quickSortTime = (endQuick - startQuick) / 1_000_000; // Convert nanoseconds to milliseconds

        // Measure RadixSort running time.
        int[] radixSortArray = Arrays.copyOf(array2, array2.length);
        long startRadix = System.nanoTime();
        Sorter.radixSort(radixSortArray);
        long endRadix = System.nanoTime();
        long radixSortTime = (endRadix - startRadix) / 1_000_000; // Convert nanoseconds to milliseconds

        // Output the measured running times.   
        System.out.println("QuickSort running time: " + quickSortTime + " ms");
        System.out.println("RadixSort running time: " + radixSortTime + " ms");

        scanner.close();
    }
}
