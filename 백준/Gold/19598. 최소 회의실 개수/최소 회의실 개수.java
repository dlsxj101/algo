import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Node implements Comparable<Node> {
        int start;
        int end;

        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.start, o.start);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine().trim());
        Node arr[] = new Node[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            arr[i] = new Node(a, b);
        }

        Arrays.sort(arr);

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        pq.add(arr[0].end);

        for (int i = 1; i < N; i++) {
            if (arr[i].start >= pq.peek()) {
                pq.poll();
            }

            pq.add(arr[i].end);
        }
        System.out.println(pq.size());
    }
}