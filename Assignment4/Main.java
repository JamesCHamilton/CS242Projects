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
 *  Collaborators: None
 *  References: ChatGPT for question 3 about the question with the formula on how to formulate a formula. 
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
 * |V | = 10        0.1226 ms                   0.087 ms                        0.15 ms
 * |V | = 100       0.4625 ms                   0.2646 ms                       4.0 ms
 * |V | = 1000      0.5757 ms                   3.6417 ms                       400 ms
 *
 * 
 * From my results, DFS takes about 0.0006 × (|V| + |E|) milliseconds to run. This makes sense because the time should grow with how many nodes and edges there are. 
    In my code, I used an adjacency list, which just means every node has a list of its neighbors. In my code, I used an adjacency list, which just means every node has a list of its neighbors.
    This is good for DFS because when we go through each node, we can quickly loop through its neighbors without wasting time. 
    Since we only visit each node and edge once, the runtime grows the way it’s supposed to.

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

