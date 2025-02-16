package Assignment1;

import java.util.Random;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter size of the array (n): ");
        int n = sc.nextInt();

        // Generate random array of length n
        // (including sign, i.e. positive/negative)
        // For example, random integers in range -100..+100
        int[] arr = new int[n];
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            arr[i] = rand.nextInt(201) - 100; 
        }

        // --- 1) Time the O(n^2) brute force ---
        long startTime = System.nanoTime();
        int maxBrute = bruteForceMaxSubarray(arr);
        long endTime = System.nanoTime();
        long bruteTime = endTime - startTime;
        System.out.println("Brute force result = " + maxBrute 
                           + " in " + bruteTime + " nanosecs.");

        // --- 2) Time the O(n log n) divide-and-conquer ---
        startTime = System.nanoTime();
        int maxDivideConquer = divideAndConquerMaxSubarray(arr, 0, arr.length - 1);
        endTime = System.nanoTime();
        long divideTime = endTime - startTime;
        System.out.println("Divide & Conquer result = " + maxDivideConquer 
                           + " in " + divideTime + " nanosecs.");

        // --- 3) (Extra Credit) Time the O(n) Kadane’s Algorithm ---
        // Uncomment if you want to include Kadane’s:
        /*
        startTime = System.nanoTime();
        int maxKadane = kadaneMaxSubarray(arr);
        endTime = System.nanoTime();
        long kadaneTime = endTime - startTime;
        System.out.println("Kadane’s (DP) result = " + maxKadane
                           + " in " + kadaneTime + " nanosecs.");
        */

        sc.close();
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
 *
 *
 *************************************************************************/