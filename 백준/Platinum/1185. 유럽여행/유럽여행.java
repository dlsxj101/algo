import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, P;
    static int parents[], C[];
    static List<Integer> adjList[];

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
        parents = new int[N + 1];
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
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        C = new int[N + 1];
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            C[i] = Integer.parseInt(br.readLine().trim());
            min = Math.min(min, C[i]);
        }

        Edge edges[] = new Edge[P];
        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            edges[i] = new Edge(a, b, (2 * c) + C[a] + C[b]);
        }
        Arrays.sort(edges);

        make();

        adjList = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        int cnt = 0;
        int moveCost = 0;
        for (Edge edge : edges) {
            if (union(edge.start, edge.end)) {
                moveCost += edge.cost;
                cnt++;

                adjList[edge.start].add(edge.end);
                adjList[edge.end].add(edge.start);

//                System.out.println("start: " + edge.start + " end:" + edge.end);
                if (cnt >= N - 1) break;
            }
        }

        int visitCost = 0;
        for (int i = 1; i <= N; i++) {
            int s = adjList[i].size();

//            System.out.println(s);
            visitCost += s * C[i];
        }

//        System.out.println("moveCost: " + moveCost);
//        System.out.println("visitCost: " + visitCost);
//        System.out.println("min: " + min);
//        System.out.println((moveCost * 2) + visitCost + min);
        System.out.println(moveCost+min);
    }
}