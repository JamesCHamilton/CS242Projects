import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Author: James Hamilton, Edmund Zhong, Hiruka Gamage Ahelpa
// Date: April 7, 2025
// Purpose/Description: implements text justification using dynamic programming
// Visible Methods and Data: badness(String[] W, int i, int j, int omega), memoizedMinimumBadness(String[] W, int i, int[] memo, int[] breaks, int omega), split(String[] W, int omega), justify(String[] W, List<Integer> L, int omega, String filename)
public class TextJustification {

    // Calculate the badness of a line
    static int badness(String[] W, int i, int j, int omega) {
        int len = 0;
        for (int k = i; k < j; k++) {
            len += W[k].length();
            if (k > i) len++; // One space between words
        }
        // If it can't fit, return "infinite" badness:
        if (omega - len < 0) {
            return Integer.MAX_VALUE;
        }
        // Otherwise, use LATEX-style cubic badness:
        return (int) Math.pow(omega - len, 3);
    }

    // Memoized Minimum Badness function
    static int memoizedMinimumBadness(String[] W, int i, int[] memo, int[] breaks, int omega) {
        // If we already solved it, return the stored answer
        if (memo[i] >= 0) return memo[i];

        // Base case: if i == W.length, no more words => badness = 0
        if (i == W.length) {
            memo[i] = 0;
            breaks[i] = W.length; 
            return 0;
        }

        // Otherwise, try all possible j from i+1..W.length
        int min = Integer.MAX_VALUE;
        int indexOfMin = i + 1; // fallback so we won't be stuck on i
        
        for (int j = i + 1; j <= W.length; j++) {
            int currentBadness = badness(W, i, j, omega);
            if (currentBadness == Integer.MAX_VALUE) {
                // The words [i..j-1] can't fit on one line; break out early if you like
                break; 
            }
            // If it fits, check total badness (current line + remainder of text)
            int total = currentBadness + memoizedMinimumBadness(W, j, memo, breaks, omega);
            if (total < min) {
                min = total;
                indexOfMin = j;
            }
        }

        memo[i] = min;
        breaks[i] = indexOfMin;
        return memo[i];
    }

    // Build the list of line breaks from the DP table
    static List<Integer> split(String[] W, int omega) {
        int[] memo = new int[W.length + 1];
        int[] breaks = new int[W.length + 1];
        // Initialize memo with -1 
        for (int i = 0; i < memo.length; i++) {
            memo[i] = -1;
        }

        // Compute the DP solution
        memoizedMinimumBadness(W, 0, memo, breaks, omega);

        // Reconstruct the breakpoints
        List<Integer> result = new ArrayList<>();
        int i = 0;
        while (i < W.length) {
            result.add(i);
            if (breaks[i] <= i || breaks[i] > W.length) {
                // Safety fallback: avoid infinite loop or out of bounds
                i++;
            } else {
                i = breaks[i];
            }
        }

        return result;
    }

    // Justify method (writes “fully justified” lines to the file)
    static void justify(String[] W, List<Integer> L, int omega, String filename) throws IOException {
        FileWriter fw = new FileWriter(filename);

        for (int idx = 0; idx < L.size(); idx++) {
            int i = L.get(idx);
            // The next line break is either the next element in L, or the end of W
            int j = (idx == L.size() - 1) ? W.length : L.get(idx + 1);

            // Compute how many total spaces must be distributed
            int spacesNeeded = omega;
            for (int k = i; k < j; k++) {
                spacesNeeded -= W[k].length();
            }

            int gaps = j - i - 1;
            for (int k = i; k < j; k++) {
                fw.write(W[k]);
                // Insert spaces if not the last word in the line
                if (k < j - 1) {
                    // Each gap gets at least floor(spacesNeeded/gaps)
                    // plus possibly 1 more if we have leftover
                    int spaces = gaps > 0 ? (spacesNeeded / gaps) : 0;
                    if (gaps > 0 && (spacesNeeded % gaps != 0)) {
                        spaces += 1;
                    }
                    for (int s = 0; s < spaces; s++) {
                        fw.write(' ');
                    }
                    spacesNeeded -= spaces;
                    gaps--;
                }
            }
            fw.write("\n");
        }

        fw.close();
    }
}
