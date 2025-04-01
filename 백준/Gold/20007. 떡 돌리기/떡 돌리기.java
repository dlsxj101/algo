import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, X, Y;
    static int dist[];
    static List<Node> adjList[];

    static class Node implements Comparable<Node> {
        int index;
        int cost;

        public Node(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        adjList = new List[N];
        dist = new int[N];
        for (int i = 0; i < N; i++) {
            adjList[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adjList[a].add(new Node(b, c));
            adjList[b].add(new Node(a, c));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean visited[] = new boolean[N];

        dist[Y] = 0;
        pq.add(new Node(Y, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            int nowVertex = now.index;

            if (visited[nowVertex]) continue;
            visited[nowVertex] = true;

            for (Node next : adjList[nowVertex]) {
                if (dist[next.index] > dist[nowVertex] + next.cost) {
                    dist[next.index] = dist[nowVertex] + next.cost;

                    pq.add(new Node(next.index, dist[next.index]));
                }
            }
        }

        Arrays.sort(dist);

        int cnt = 1;
        int current = 0;
        for (int i = 1; i < N; i++) {
            if (dist[i] == Integer.MAX_VALUE || dist[i] * 2 > X) {
                System.out.println(-1);
                return;
            }

            current += dist[i] * 2;

            if (current > X) {
                cnt++;
                current = dist[i] * 2;
            }
        }
        System.out.println(cnt);
    }
}