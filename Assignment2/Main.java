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
 * 10000    0ms     0ms     0ms     5ms     25ms    Error
 * 100000   0ms     0ms     0ms     4ms     24ms    Error
 *      
 * Radix Sort Running Time
 *          1      10     100      1000    10000   100000      -n
 * 1        0ms     0ms     0ms     0ms     2ms     11ms
 * 10       0ms     0ms     0ms     0ms     2ms     11ms
 * 100      0ms     0ms     0ms     0ms     2ms     12ms
 * 1000     0ms     0ms     0ms     0ms     2ms     11ms
 * 10000    0ms     0ms     0ms     0ms     2ms     Error
 * 100000   0ms     0ms     0ms     0ms     2ms     Error
 * 
 * 
 * 
 * 4. Based on the running times observed, it seems as though Radix sort is a better sorting algorithm than quicksort.
 * This is because, in every single iteration of n and r, radix sort does it in less time than quick sort.
 * Both alogirhtms were similar until n got to 1000. Quick sort started taking between 1-5ms when n was 1000 while
 * Radix sort continued to take 0ms to sort it. This is more drastic when n is 10000 and 100000.
 * When n was 10000, quick sort went from 5ms at r=100 to 16ms at r=1000. Radix sort continued to do it in
 * 2ms for when n was 10000. When n because 100000, radix sort did take longer at around 11-12ms but quick sort still
 * did it from 19ms at r=1 to 82ms at r=1000. Yes, the repetitions do have an impact but mostly for quick sort. In quick sort when n
 * became 1000, as r increased, so did the runtime. this is a continuing trend when n was greater than 1000 such as in n= 10000 
 * when r was 1, it was 2ms but when r was 100000, it became 24 ms. This was only true for quick sort, on the other hand, radix sort
 * continued to take the same time even though r increased. When n was 10000, and r was increased, it still was 2ms for radix sort.
 * For instance, when n= 10000 and r = 100, quick sort took 5ms but when n was increased to 100000 the run time increased to 25ms 
 * which indicates a rougly linear increase. As r increases, quicksort's running time shows a noticable increase showing that
 * the number of repetitions does have an impact on its performance. Radix sort running times stable as the number of repetitions increase showing
 * that it is less affected by the number of repetitions. 
 */