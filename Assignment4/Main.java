package Assignment4;
import java.util.Random;
import java.util.Scanner;
import Assignment4.DFS.Graph;

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

