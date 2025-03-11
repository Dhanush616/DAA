import java.util.*;

class WaterSupplyNetworkMST {
    private int vertices;
    private int[][] graph;

    public WaterSupplyNetworkMST(int vertices) {
        this.vertices = vertices;
        graph = new int[vertices][vertices];
    }

    public void addEdge(char source, char destination, int weight) {
        int u = source - 'A';
        int v = destination - 'A';
        graph[u][v] = weight;
        graph[v][u] = weight;
    }

    private int minKey(int[] key, boolean[] mstSet) {
        int min = Integer.MAX_VALUE, minIndex = -1;

        for (int v = 0; v < vertices; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    public void primMST() {
        int[] parent = new int[vertices];
        int[] key = new int[vertices];
        boolean[] mstSet = new boolean[vertices];

        Arrays.fill(key, Integer.MAX_VALUE);
        key[0] = 0;
        parent[0] = -1;

        for (int count = 0; count < vertices - 1; count++) {
            int u = minKey(key, mstSet);
            mstSet[u] = true;

            for (int v = 0; v < vertices; v++) {
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        System.out.println("Edges in the Minimum Spanning Tree:");
        for (int i = 1; i < vertices; i++) {
            System.out.println((char) (parent[i] + 'A') + " - " + (char) (i + 'A') + " : " + graph[i][parent[i]]);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of vertices: ");
        int vertices = scanner.nextInt();
        
        WaterSupplyNetworkMST network = new WaterSupplyNetworkMST(vertices);
        
        System.out.print("Enter the number of edges: ");
        int edges = scanner.nextInt();

        System.out.println("Enter the edges (source, destination, weight) with source and destination as alphabets:");
        for (int i = 0; i < edges; i++) {
            char source = scanner.next().charAt(0);
            char destination = scanner.next().charAt(0);
            int weight = scanner.nextInt();
            network.addEdge(source, destination, weight);
        }
        
        network.primMST();
        scanner.close();
    }
}
