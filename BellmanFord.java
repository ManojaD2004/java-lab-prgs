import java.util.Scanner;

public class BellmanFord {
    private int D[];
    private int NoV;
    public static final int MAX_VALUE = 999;

    public BellmanFord(int NoV) {
        this.NoV = NoV;
        D = new int[NoV + 1];
    }

    public void BellmanFordEvaluation(int source, int A[][]) {
        for (int node = 1; node <= NoV; node++) {
            D[node] = MAX_VALUE;
        }
        D[source] = 0;
        for (int node = 1; node <= NoV - 1; node++) {
            for (int i = 1; i <= NoV; i++) {
                for (int j = 1; j <= NoV; j++) {
                    if (A[i][j] != MAX_VALUE) {
                        if (D[j] > D[i] + A[i][j])
                            D[j] = D[i] + A[i][j];
                    }
                }
            }
        }
        for (int i = 1; i <= NoV; i++) {
            for (int j = 1; j <= NoV; j++) {
                if (A[i][j] != MAX_VALUE) {
                    if (D[j] > D[i] + A[i][j]) {
                        System.out.println("The Graph contains negative egde cycle");
                        return;
                    }
                }
            }
        }
        for (int vertex = 1; vertex <= NoV; vertex++) {
            System.out.println("distance of source " + source + " to " + vertex + " is "
                    + D[vertex]);
        }
    }

    public static void main(String... arg) {
        int NoV = 0;
        int source;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of vertices");
        NoV = scanner.nextInt();
        int A[][] = new int[NoV + 1][NoV + 1];
        System.out.println("Enter the adjacency matrix");
        for (int i = 1; i <= NoV; i++) {
            for (int j = 1; j <= NoV; j++) {
                A[i][j] = scanner.nextInt();
            }
        }
        System.out.println("Enter the source vertex");
        source = scanner.nextInt();
        BellmanFord bellmanford = new BellmanFord(NoV);
        bellmanford.BellmanFordEvaluation(source, A);
        scanner.close();
    }
}