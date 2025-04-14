import java.util.Scanner;

public class FMalgo {

    // Floyd-Warshall: Calculates all-pairs shortest paths
    static void floyd(int D[][], int n) {
        for (int k = 1; k <= n; k++)
            for (int i = 1; i <= n; i++)
                for (int j = 1; j <= n; j++)
                    D[i][j] = Math.min(D[i][j], D[i][k] + D[k][j]);
    }

    // Warshall: Computes transitive closure (reachability)
    static void warshall(int D[][], int n) {
        for (int k = 1; k <= n; k++)
            for (int i = 1; i <= n; i++)
                for (int j = 1; j <= n; j++)
                    D[i][j] = (D[i][j] != 0 || (D[i][k] != 0 && D[k][j] != 0)) ? 1 : 0;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n;

        System.out.println("Enter the number of vertices:");
        n = s.nextInt();

        // 1-based indexing
        int[][] matrix = new int[n + 1][n + 1];

        System.out.println("Enter the matrix:");
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= n; j++)
                matrix[i][j] = s.nextInt();

        System.out.println("Select algorithm:");
        System.out.println("1. Floyd-Warshall (All pairs shortest path)");
        System.out.println("2. Warshall (Transitive Closure)");

        int choice = s.nextInt();

        switch (choice) {
            case 1:
                floyd(matrix, n);
                System.out.println("All-pair shortest path matrix:");
                break;
            case 2:
                warshall(matrix, n);
                System.out.println("Transitive closure matrix:");
                break;
            default:
                System.out.println("Invalid choice.");
                s.close();
                return;
        }

        // Print final matrix
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++)
                System.out.print(matrix[i][j] + "  ");
            System.out.println();
        }

        s.close();
    }
}
