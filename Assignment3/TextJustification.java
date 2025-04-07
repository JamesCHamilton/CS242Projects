import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TextJustification {
   
    // Calculate the badness of a line
    static int badness(String[] W, int i, int j, int omega) {
        int len = 0;
        for (int k = i; k < j; k++) {
            len += W[k].length();
            if (k > i) len++; // Space between words
        }
        return omega - len >= 0 ? (int) Math.pow(omega - len, 3) : Integer.MAX_VALUE;
    }

    // Memoized Minimum Badness function
    static int memoizedMinimumBadness(String[] W, int i, int[] memo, int[] breaks, int omega) {
        if (memo[i] >= 0) return memo[i];
        if (i == W.length) {
            memo[i] = 0;
            breaks[i] = W.length;
        } else {
            int min = Integer.MAX_VALUE;
            int index_of_min = i;
            for (int j = i + 1; j <= W.length; j++) {
                int temp = badness(W, i, j, omega);
                if (temp < Integer.MAX_VALUE) {
                    temp += memoizedMinimumBadness(W, j, memo, breaks, omega);
                    if (temp < min) {
                        min = temp;
                        index_of_min = j;
                    }
                }
            }
            memo[i] = min;
            breaks[i] = index_of_min;
        }
        return memo[i];
    }

    // Split function
    static List<Integer> split(String[] W, int omega) {
        int[] memo = new int[W.length + 1];
        int[] breaks = new int[W.length + 1];
        for (int i = 0; i <= W.length; i++) memo[i] = -1;

        memoizedMinimumBadness(W, 0, memo, breaks, omega);

        List<Integer> result = new ArrayList<>();
        int i = 0;
        while (i < W.length) {
            result.add(i);
            i = breaks[i];
        }
        return result;
    }

    // Justify method
    static void justify(String[] W, List<Integer> L, int omega, String filename) throws IOException {
        FileWriter fw = new FileWriter(filename);
        for (int idx = 0; idx < L.size(); idx++) {
            int i = L.get(idx);
            int j = (idx == L.size() - 1) ? W.length : L.get(idx + 1);
            int spaces_needed = omega;
            for (int k = i; k < j; k++) spaces_needed -= W[k].length();

            int gaps = j - i - 1;
            for (int k = i; k < j; k++) {
                fw.write(W[k]);
                if (k < j - 1) {
                    int spaces = spaces_needed / gaps + ((spaces_needed % gaps > 0) ? 1 : 0);
                    spaces_needed -= spaces;
                    gaps--;
                    for (int s = 0; s < spaces; s++) fw.write(" ");
                }
            }
            fw.write("\n");
        }
        fw.close();
    }
}