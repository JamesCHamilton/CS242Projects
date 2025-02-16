package Assignment1;

public class MaxSubArray {
    
    public static int bruteForce(int[] a){return bruteForceHelper(a);}
    public static int divideAndConquerMaxSubarray(int[] a, int low, int high){return divideAndConquerHelper(a, low, high);}
    public static int kadaneMaxSubarray(int[] a){return kadaneMaxSubarrayHelper(a);}

    private static int bruteForceHelper(int[] a){
        int maxSub = Integer.MIN_VALUE;
        int length = a.length;
        //first pointer to the first element
        for(int i = 0; i < length; i++){
            int sum = 0;
            //second pointer to the number after the "i" element
            for(int j = i; j < length; j++){
                sum = sum + a[j];
                maxSub = Integer.max(maxSub, sum);
            }
        } 
        return maxSub;
    }


    private static int divideAndConquerHelper(int[] a, int low, int high){
            if (low == high) {
                // Base case: only one element
                return a[low];
            }
            int mid = (low + high) / 2;
            // maximum subarray sum in left half
            int leftSum = divideAndConquerHelper(a, low, mid);
            // maximum subarray sum in right half
            int rightSum = divideAndConquerHelper(a, mid + 1, high);
            // maximum subarray sum crossing the midpoint
            int crossSum = maxCrossingSubarray(a, low, mid, high);
    
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

    private static int kadaneMaxSubarrayHelper(int[] A) {
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
