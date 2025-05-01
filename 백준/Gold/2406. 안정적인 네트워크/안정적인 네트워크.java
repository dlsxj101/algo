import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, m, X, K, parents[];

    static void make() {
        parents = new int[n + 1];
        Arrays.fill(parents, -1);
    }

    static int findSet(int a) {
        if (parents[a] < 0) return a;
        return parents[a] = findSet(parents[a]);
    }

    static boolean union(int a, int b) {
        int rootA = findSet(a);
        int rootB = findSet(b);
        if (rootA == rootB) return false;

        parents[rootA] += parents[rootB];
        parents[rootB] = rootA;
        return true;
    }

    static class Edge implements Comparable<Edge> {
        int start;
        int end;
        int cost;

        public Edge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        make();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            union(a, b);
        }

        int costMatrix[][] = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                costMatrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        List<Edge> edges = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                edges.add(new Edge(i, j, costMatrix[i][j]));
            }
        }
        Collections.sort(edges);

        X = 0;
        K = 0;
        for (Edge e : edges) {
            if (union(e.start, e.end)) {
                X += e.cost;
                K++;
                sb.append(e.start).append(" ").append(e.end).append("\n");
            }
        }
        System.out.println(X + " " + K);
        System.out.println(sb);
    }
}