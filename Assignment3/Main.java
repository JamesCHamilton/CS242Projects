import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

// Author: James Hamilton, Edmund Zhong, Hiruka Gamage Ahelpa
// Date: April 7,2025
// Purpose/Description: Testing time output of the algorithms found in TextJustification.java
// Visible Methods and Data: main(String[] args)

/*************************************************************************
 *
 *  Pace University
 *  Spring 2024
 *  Algorithms and Computing Theory
 *
 *  Course: CS 242
 *  Team members: James Hamilton, Edmund Zhong, Hiruka Gamage Ahelpa
 *  Collaborators: PUT THE NAME OF ANY COLLABORATORS OUTSIDE YOUR TEAM HERE, IF NONE, PUT NONE
 *  References: PUT THE LINKS TO YOUR SOURCES HERE
 *
 *  Assignment: 3
 *  Problem: 3
 *  Description: Dynamic Programming is a powerful technique that explores all possible cases to obtain a solution, yet achieves an exponential improvement in
running time when compared with a simpler brute-force approach. A wellknown example of Dynamic Programming usage is Text Justification, where
we are given an array of words and a page width and we have to decide how
to split the text so that it looks “nice” after justifying against margins. The
purpose of this homework is to compare experimentally the result obtained
justifying text with a greedy algorithm (as used by MS Word) against doing
the same using LATEX rules and Dynamic Programming. An example of text
justification using Dynamic Programming is attached.
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
 *  5. The result of just.txt and unjust.txt are different. They hold they same amount of letters in this case, a's. The just line appears vertical because it has line breaks.
 * The unjust appears horizontal because there are only spaces between the letters instead of just.txt that has an entire line between the letters. 
 * MS word is using a greedy approach because it does it in the shortest time possible which in this case is having a space between the letters instead of having an entire line difference.
 * This approach is greedy because it is an algorithm that finds the solution to the problem in the shortest amount of time. 
 * 
 * 6. just.txt tends to follow a more regular punctuation and spacing pattern, whereas unjust.txt includes more erratic placement of commas, periods, and spacing, implying more distortion or corruption.
unjust.txt introduces extra characters or alterations in chunks, disrupting some sequences that were intact in just.txt.
source = https://libraryofbabel.info/book.cgi?2p-w1-s1-v23:114
 *
 *************************************************************************/

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of words: ");
        int n = sc.nextInt();
        System.out.print("Enter page width: ");
        int omega = sc.nextInt();

        Random random = new Random();
        String[] W = new String[n];
        
        for (int i = 0; i < n; i++) {
            // We allow word lengths up to 15
            int len = random.nextInt(15) + 1;
            // If the generated word is bigger than omega, you either skip it or handle it differently
            // For now, just ensure it doesn't exceed omega by capping:
            if (len > omega) {
                len = omega; 
            }
            W[i] = "a".repeat(len);
        }

        // File inputFile = new File("C:/Users/meela/Downloads/Classes/CS242Projects/Assignment3/input.txt");
        // Scanner fileScanner = new Scanner(inputFile);
        // List<String> wordList = new ArrayList<>();

        // while (fileScanner.hasNext()) {
        //     wordList.add(fileScanner.next());
        // }

        // fileScanner.close();

        // String[] W = wordList.toArray(new String[0]);

        List<Integer> L = TextJustification.split(W, omega);
        TextJustification.justify(W, L, omega, "just.txt");

        // Also write out the "unjustified" version
        FileWriter fw = new FileWriter("unjust.txt");
        for (String word : W) {
            fw.write(word + " ");
        }
        fw.close();

        System.out.println("Files 'just.txt' and 'unjust.txt' created successfully.");
        sc.close();
    }
}