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

/*Pace University
 *  Spring 2024
 *  Algorithms and Computing Theory
 *
 *  Course: CS 242
 *  Team members: Edmund Zhong, James Hamilton, Hiruka Gamage
 *  Collaborators: None
 *  References: None
 *
 *  Assignment: 2
*  Problem: Quick sort and Radix sort runtime
 *  Description: We have studied a variety of sorting algorithms assuming that the input
does not have repetitions for simplicity. Under such assumption, we have
computed upper bounds on the running time of each algorithm for the worstcase input. But how good/bad are these algorithms if we know that the
input has a lot of repetitions? The purpose of this assignment is to evaluate
experimentally the time performance of two sorting algorithms on inputs that
have many repeated items. Specifically, do the following.
 *
 *  Input: 
 *  Output: 
 *
 *  Visible data fields:
 *  COPY DECLARATION OF VISIBLE DATA FIELDS HERE
 *
 *  Visible methods:
 *  COPY SIGNATURE OF VISIBLE METHODS HERE
 *
 *
 *   Remarks
 *   -------
 *
 *   PUT ALL NON-CODING ANSWERS HERE 
 * Quicksort Running Time
 *          1      10     100      1000    10000   100000      -n
 * 1        0ms     0ms    0ms      0ms     2ms     19ms  
 * 10       0ms     0ms     0ms     1ms     2ms     17ms
 * 100      0ms     0ms     0ms     1ms     5ms     25ms
 * 1000     0ms     0ms     0ms     4ms     16ms    82ms
 * 10000    0ms     0ms     0ms     5ms     25ms
 * 100000   0ms     0ms     0ms     4ms     24ms
 *      
 * Radix Sort Running Time
 *          1      10     100      1000    10000   100000      -n
 * 1        0ms     0ms     0ms     0ms     2ms     11ms
 * 10       0ms     0ms     0ms     0ms     2ms     11ms
 * 100      0ms     0ms     0ms     0ms     2ms     12s
 * 1000     0ms     0ms     0ms     0ms     2ms     11ms
 * 10000    0ms     0ms     0ms     0ms     2ms
 * 100000   0ms     0ms     0ms     0ms     2ms
 * 
 * */