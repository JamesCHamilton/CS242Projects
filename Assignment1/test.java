package Assignment1;
    
    public class test {
    
        // --------------------------------------------------------
        // ALGORITHM 1: Brute force (O(n^2))
        // --------------------------------------------------------
        public static int bruteForceMaxSubarray(int[] A) {
            int n = A.length;
            int maxSum = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                int currentSum = 0;
                for (int j = i; j < n; j++) {
                    currentSum += A[j];
                    if (currentSum > maxSum) {
                        maxSum = currentSum;
                    }
                }
            }
            return maxSum;
        }
    
        // --------------------------------------------------------
        // ALGORITHM 2: Divide and Conquer (O(n log n))
        // --------------------------------------------------------
        public static int divideAndConquerMaxSubarray(int[] A, int low, int high) {
            if (low == high) {
                // Base case: only one element
                return A[low];
            }
            int mid = (low + high) / 2;
            // maximum subarray sum in left half
            int leftSum = divideAndConquerMaxSubarray(A, low, mid);
            // maximum subarray sum in right half
            int rightSum = divideAndConquerMaxSubarray(A, mid + 1, high);
            // maximum subarray sum crossing the midpoint
            int crossSum = maxCrossingSubarray(A, low, mid, high);
    
            // Return overall maximum of the three
            return Math.max(Math.max(leftSum, rightSum), crossSum);
        }
    
        private static int maxCrossingSubarray(int[] A, int low, int mid, int high) {
            // sum for left side
            int leftSum = Integer.MIN_VALUE;
            int sum = 0;
            for (int i = mid; i >= low; i--) {
                sum += A[i];
                if (sum > leftSum) {
                    leftSum = sum;
                }
            }
            // sum for right side
            int rightSum = Integer.MIN_VALUE;
            sum = 0;
            for (int j = mid + 1; j <= high; j++) {
                sum += A[j];
                if (sum > rightSum) {
                    rightSum = sum;
                }
            }
            return leftSum + rightSum;
        }
    
        // --------------------------------------------------------
        // ALGORITHM 3: Kadane’s Dynamic Programming (O(n)) [Extra Credit]
        // --------------------------------------------------------
        public static int kadaneMaxSubarray(int[] A) {
            int maxEndingHere = A[0];
            int maxSoFar = A[0];
            for (int i = 1; i < A.length; i++) {
                // Either extend the current subarray or start a new one
                maxEndingHere = Math.max(A[i], maxEndingHere + A[i]);
                // Track the best subarray so far
                maxSoFar = Math.max(maxSoFar, maxEndingHere);
            }
            return maxSoFar;
        }
    }
