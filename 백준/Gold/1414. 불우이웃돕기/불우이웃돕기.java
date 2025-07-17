import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, parents[];

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

    static void make() {
        parents = new int[N];
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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine().trim());

        make();

        List<Edge> edges = new ArrayList<>();

        int total = 0;
        for (int i = 0; i < N; i++) {
            String input = br.readLine().trim();
            for (int j = 0; j < N; j++) {
                char tmp = input.charAt(j);

                if (tmp == '0') continue;

                int tmpNum;

                if (tmp >= 'a') tmpNum = tmp - 'a' + 1;
                else tmpNum = tmp - 'A' + 27;


                total += tmpNum;
                edges.add(new Edge(i, j, tmpNum));
            }
        }

        Collections.sort(edges);

        int sum = 0;
        int cnt = 0;

        for (Edge edge : edges) {
            if (edge.start == edge.end) continue;

            if (union(edge.start, edge.end)) {
                cnt++;
                sum += edge.cost;
            }
        }

//        System.out.println("total = " + total);
//        System.out.println("sum = " + sum);
        
        if (cnt < N - 1) System.out.println(-1);
        else if (sum == 0) {
            if (N == 1) System.out.println(total);
            else System.out.println(-1);
        } else System.out.println(total - sum);
    }
}
