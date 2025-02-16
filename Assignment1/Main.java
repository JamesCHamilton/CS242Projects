package Assignment1;

import java.util.Random;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter size of the array (n): ");
        int n = scanner.nextInt();

        // Generate random array of length n
        // (including sign, i.e. positive/negative)
        // For example, random integers in range -100..+100 (note to self) 
        int[] arr = new int[n];
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            arr[i] = rand.nextInt(201) - 100; 
        }

        // --- 1) Time the O(n^2) brute force ---
        long startTime = System.nanoTime();
        int maxBrute = MaxSubArray.bruteForce(arr);
        long endTime = System.nanoTime();
        long bruteTime = endTime - startTime;
        System.out.println("Brute force result = " + maxBrute + " in " + bruteTime + " nanosecs.");

        // --- 2) Time the O(n log n) divide-and-conquer ---
        startTime = System.nanoTime();
        int maxDivideConquer = MaxSubArray.divideAndConquerMaxSubarray(arr, 0, arr.length - 1);
        endTime = System.nanoTime();
        long divideTime = endTime - startTime;
        System.out.println("Divide & Conquer result = " + maxDivideConquer + " in " + divideTime + " nanosecs.");

        // --- 3) (Extra Credit) Time the O(n) Kadane’s Algorithm ---
        // Uncomment if you want to include Kadane’s:
        
        startTime = System.nanoTime();
        int maxKadane = MaxSubArray.kadaneMaxSubarray(arr);
        endTime = System.nanoTime();
        long kadaneTime = endTime - startTime;
        System.out.println("Kadane’s (DP) result = " + maxKadane + " in " + kadaneTime + " nanosecs.");

        scanner.close();
    }
}


/*************************************************************************
 *
 *  Pace University
 *  Spring 2024
 *  Algorithms and Computing Theory
 *
 *  Course: CS 242
 *  Team members: James Hamilton, Edmund Zhong
 *  Collaborators: None
 *  References: PUT THE LINKS TO YOUR SOURCES HERE
 *
 *  Assignment: 1
 *  Problem:  Maximum Subarray Problem
 *  Description: The Maximum Subarray Problem is the task of finding the contiguous
subarray, within an array of numbers, that has the largest sum. For example, for the sequence of values (−2, 1, −3, 4, −1, 2, 1, −5, 4) the contiguous
subsequence with the largest sum is (4, −1, 2, 1), with sum 6.
For an arbitrary input array of length n, two algorithms that compute
the sum of the maximum subarray were discussed in class: (a) a brute-force
algorithm that solves the problem in O(n
2
) steps, and (b) a divide-andconquer algorithm that achieves O(n log n) running time.

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
 *                          n = 10^1            n = 10^2            n = 10^3            n = 10^4            n= 10^5                 
 * 2. Brute force           529400 nanosecs     765300 nanosecs     4383600 nanosecs    39472200 nanosecs   3232845400 nanosecs
 *    Divide and conquer    33300 nanosecs      64400 nanosecs      489000 nanosecs     2007300 nanosecs    13636600 nanosecs
 *    Kadanes               6200 nanosecs       10200 nanosecs      46100 nanosecs      490500 nanosecs     3416500 nanosecs
 *
 *
 * 3. Our data clearly shows that the divide and conquer algorithm is alot faster than the brute force algorithm. It proves that O(n log n) is faster than O(n^2). 
 *************************************************************************/