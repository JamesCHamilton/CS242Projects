import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

// Author: James Hamilton, Edmund Zhong, Hiruka Gamage Ahelpa
// Date: March 7,2025
// Purpose/Description: Testing time output of the algorithms found in TextJustification.java
// Visible Methods and Data: main(String[] args)
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
            int len = random.nextInt(15) + 1;
            W[i] = "a".repeat(len);
        }

        List<Integer> L = TextJustification.split(W, omega);
        TextJustification.justify(W, L, omega, "just.txt");

        FileWriter fw = new FileWriter("unjust.txt");
        for (String word : W) fw.write(word + " ");
        fw.close();

        System.out.println("Files 'just.txt' and 'unjust.txt' created successfully.");
        sc.close();
    }
}
