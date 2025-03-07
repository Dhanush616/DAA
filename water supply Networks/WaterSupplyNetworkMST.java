import java.util.*;

class Edge implements Comparable<Edge> {
    int source, destination, weight;

    public Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge otherEdge) {
        return this.weight - otherEdge.weight;
    }
}

class Subset {
    int parent, rank;
}

public class WaterSupplyNetworkMST {
    int vertices, edgesCount;
    Edge[] edges;

    public WaterSupplyNetworkMST(int vertices, int edgesCount) {
        this.vertices = vertices;
        this.edgesCount = edgesCount;
        edges = new Edge[edgesCount];
    }

    int find(Subset[] subsets, int i) {
        if (subsets[i].parent != i) {
            subsets[i].parent = find(subsets, subsets[i].parent);
        }
        return subsets[i].parent;
    }

    void union(Subset[] subsets, int x, int y) {
        int xRoot = find(subsets, x);
        int yRoot = find(subsets, y);

        if (subsets[xRoot].rank < subsets[yRoot].rank) {
            subsets[xRoot].parent = yRoot;
        } else if (subsets[xRoot].rank > subsets[yRoot].rank) {
            subsets[yRoot].parent = xRoot;
        } else {
            subsets[yRoot].parent = xRoot;
            subsets[xRoot].rank++;
        }
    }

    public void mst() {
        Edge[] result = new Edge[vertices - 1];
        int edgeIndex = 0;
        int resultIndex = 0;

        Arrays.sort(edges);

        Subset[] subsets = new Subset[vertices];
        for (int v = 0; v < vertices; ++v) {
            subsets[v] = new Subset();
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }

        while (resultIndex < vertices - 1 && edgeIndex < edgesCount) {
            Edge nextEdge = edges[edgeIndex++];

            int x = find(subsets, nextEdge.source);
            int y = find(subsets, nextEdge.destination);

            if (x != y) {
                result[resultIndex++] = nextEdge;
                union(subsets, x, y);
            }
        }

        System.out.println("Edges in the Minimum Spanning Tree:");
        for (int i = 0; i < resultIndex; ++i) {
            System.out.println((char)(result[i].source + 'A') + " - " + (char)(result[i].destination + 'A') + " : " + result[i].weight);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of vertices: ");
        int vertices = scanner.nextInt();

        System.out.print("Enter the number of edges: ");
        int edgesCount = scanner.nextInt();

        WaterSupplyNetworkMST graph = new WaterSupplyNetworkMST(vertices, edgesCount);

        System.out.println("Enter the edges (source, destination, weight) with source and destination as alphabets:");
        for (int i = 0; i < edgesCount; i++) {
            char sourceChar = scanner.next().charAt(0);
            char destinationChar = scanner.next().charAt(0);
            int weight = scanner.nextInt();
            int source = sourceChar - 'A';
            int destination = destinationChar - 'A';
            graph.edges[i] = new Edge(source, destination, weight);
        }

        graph.mst();
        scanner.close();
    }
}
