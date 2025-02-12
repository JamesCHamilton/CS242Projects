package Assignment1;

public class MaxSubArray {
    
    public static int bruteForce(int[] a){return bruteForceHelper(a);}

    private static int bruteForceHelper(int[] a){
        int maxSub = Integer.MIN_VALUE;
        int length = a.length;
        for(int i = 0; i < length; i++){
            int sum = 0;
            for(int j = i; j < length; j++){
                sum = sum + a[j];
                maxSub = Integer.max(maxSub, sum);
            }
        } 
        return maxSub;
    }


    private static int divideAndConquer(int[] a, int low, int high){



        return 0;//TODO
    }
}
