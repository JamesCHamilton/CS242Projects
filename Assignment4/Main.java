package Assignment4;
import java.util.Random;
import java.util.Scanner;
import Assignment4.DFS.Graph;
// Author: James Hamilton, Edmund Zhong, Hiruka Gamage Ahelpa
// Date: April 20,2025
// Purpose/Description: evaluating experimentally the performance of an efficient implementation of DFS. 
// Visible Methods and Data: main(String[] args)

/*************************************************************************
 *
 *  Pace University
 *  Spring 2024
 *  Algorithms and Computing Theory
 *
 *  Course: CS 242
 *  Team members: Edmund Zhong, James Hamilton, Hiruka Gamage
 *  Collaborators: PUT THE NAME OF ANY COLLABORATORS OUTSIDE YOUR TEAM HERE, IF NONE, PUT NONE
 *  References: PUT THE LINKS TO YOUR SOURCES HERE
 *
 *  Assignment: 4
 *  Problem: Depth First Search 
 *  Description: Depth First Search (DFS) is a fundamental graph traversal
used as building block to solve important graph problems such as Topological
Sort, finding Strongly Connected Components, and others. Hence, the time
efficiency of those algorithms depends heavily on implementing efficiently
DFS. Given a directed graph G = {V, E} encoded as an adjacency list, an
efficient implementation of DFS runs in O(|V | + |E|) steps. The purpose of
this homework is to evaluate experimentally the performance of an efficient
implementation of DFS.
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
 * 2.               |E| = |V | − 1              |E| = ⌊(|V | − 1)3/2            |E| = (|V | − 1)2
⌋
 * |V | = 10        0.1226 ms                   0.087 ms                        
 * |V | = 100       
 * |V | = 1000
 *
 * 
 * ) Give an approximate formula (with constants, not big-O)
for the asymptotic running time of DFS based on your experiments.
How does this compare with the expected O(|V | + |E|)? If the results
3
differ, overview the code of the data structures used for the adjacency
list and explain what might have happened.
Rubric: No points if you do not explain how you estimate the constants
column by column and row by row. Partial credit if the explanation of
your calculation does not match your measurements.
 *************************************************************************/

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();
        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();

        Graph g = new Graph(V);
        Random rand = new Random();

        // Generate random directed edges
        for (int i = 0; i < E; i++) {
            int u = rand.nextInt(V);
            int v = rand.nextInt(V);
            while (u == v || g.adj.get(u).contains(v)) {
                v = rand.nextInt(V);
            }
            g.addEdge(u, v);
        }

        long startTime = System.nanoTime();
        g.DFS();
        long endTime = System.nanoTime();

        g.printTimes();

        System.out.println("\nRunning time: " + (endTime - startTime) / 1e6 + " ms");

        sc.close();
    }
}

