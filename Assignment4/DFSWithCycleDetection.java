package Assignment4;
import java.util.*;


// Author: James Hamilton, Edmund Zhong, Hiruka Gamage Ahelpa
// Date: April 20,2025
// Purpose/Description: evaluating experimentally the performance of an efficient implementation of DFS with a graph with cycles. 
// Visible Methods and Data: Graph, DFSWithCycleDetection, main(String[] args)

public class DFSWithCycleDetection {
    enum Color { WHITE, GRAY, BLACK }

    static class Graph {
        int V;
        List<List<Integer>> adj;
        int[] discovery;
        int[] finish;
        Color[] color;
        boolean hasCycle;
        int time;

        Graph(int V) {
            this.V = V;
            adj = new ArrayList<>();
            for (int i = 0; i < V; i++) {
                adj.add(new ArrayList<>());
            }
            discovery = new int[V];
            finish = new int[V];
            color = new Color[V];
            Arrays.fill(color, Color.WHITE);
            hasCycle = false;
            time = 0;
        }

        void addEdge(int u, int v) {
            adj.get(u).add(v); // directed edge
        }

        void DFS() {
            time = 0;
            Arrays.fill(color, Color.WHITE);
            hasCycle = false;

            for (int u = 0; u < V; u++) {
                if (color[u] == Color.WHITE) {
                    DFSVisit(u);
                }
            }
        }

        void DFSVisit(int u) {
            time++;
            discovery[u] = time;
            color[u] = Color.GRAY;

            for (int v : adj.get(u)) {
                if (color[v] == Color.WHITE) {
                    DFSVisit(v);
                } else if (color[v] == Color.GRAY) {
                    // Found a back edge: cycle detected
                    hasCycle = true;
                }
            }

            color[u] = Color.BLACK;
            time++;
            finish[u] = time;
        }

        boolean containsCycle() {
            return hasCycle;
        }

        void printTimes() {
            System.out.println("\nVertex | Discovery | Finish");
            for (int i = 0; i < V; i++) {
                System.out.printf("  %d    |     %d      |   %d\n", i, discovery[i], finish[i]);
            }
        }
    }

    public static Graph generateRandomGraph(int V, int E, boolean makeAcyclic) {
        Graph g = new Graph(V);
        Random rand = new Random();

        Set<String> added = new HashSet<>();

        for (int i = 0; i < E; ) {
            int u = rand.nextInt(V);
            int v = rand.nextInt(V);

            if (u == v || added.contains(u + "," + v)) continue;
            if (makeAcyclic && v <= u) continue; // ensure u < v to avoid cycles

            g.addEdge(u, v);
            added.add(u + "," + v);
            i++;
        }

        return g;
    }

    public static void main(String[] args) {
        int V = 1000;
        int E = 3000;

        System.out.println("Graph WITHOUT cycle:");
        Graph g1 = generateRandomGraph(V, E, true);
        g1.DFS();
        System.out.println("Contains cycle? " + g1.containsCycle());

        System.out.println("\nGraph WITH cycle:");
        Graph g2 = generateRandomGraph(V, E, false);
        g2.DFS();
        System.out.println("Contains cycle? " + g2.containsCycle());
    }
}