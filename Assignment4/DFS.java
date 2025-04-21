package Assignment4;

import java.util.*;

// Author: James Hamilton, Edmund Zhong, Hiruka Gamage Ahelpa
// Date: April 20,2025
// Purpose/Description: evaluating experimentally the performance of an efficient implementation of DFS. 
// Visible Methods and Data: addEdge(int u, int v), DFS(), printTimes(), DFSVisit(int u)

public class DFS {
    static class Graph {
        int V;
        List<List<Integer>> adj;
        int time;
        int[] discovery;
        int[] finish;
        boolean[] visited;

        Graph(int V) {
            this.V = V;
            adj = new ArrayList<>();
            for (int i = 0; i < V; i++) {
                adj.add(new ArrayList<>());
            }
            discovery = new int[V];
            finish = new int[V];
            visited = new boolean[V];
            time = 0;
        }

        void addEdge(int u, int v) {
            adj.get(u).add(v); // directed edge
        }

        void DFS() {
            Arrays.fill(visited, false);
            Arrays.fill(discovery, -1);
            Arrays.fill(finish, -1);
            time = 0;

            for (int u = 0; u < V; u++) {
                if (!visited[u]) {
                    DFSVisit(u);
                }
            }
        }

        void DFSVisit(int u) {
            time++;
            discovery[u] = time;
            visited[u] = true;

            for (int v : adj.get(u)) {
                if (!visited[v]) {
                    DFSVisit(v);
                }
            }

            time++;
            finish[u] = time;
        }

        void printTimes() {
            System.out.println("\nVertex | Discovery | Finish");
            for (int i = 0; i < V; i++) {
                System.out.printf("  %d    |     %d      |   %d\n", i, discovery[i], finish[i]);
            }
        }
    }
}