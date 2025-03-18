import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, m, q;
    static List<Integer> adjList[];


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        adjList = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adjList[a].add(b);
            adjList[b].add(a);
        }

        q = Integer.parseInt(br.readLine().trim());

        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a == 1) {
                adjList[b].add(c);
                adjList[c].add(b);
            } else if (a == 2) {
                for (int j = 0; j < adjList[b].size(); j++) {
                    if (adjList[b].get(j) == c) {
                        adjList[b].remove(j);
                        break;
                    }
                }
                for (int j = 0; j < adjList[c].size(); j++) {
                    if (adjList[c].get(j) == b) {
                        adjList[c].remove(j);
                        break;
                    }
                }
            }

            int tmp[] = bfs(1);

            for(int j = 1; j < tmp.length; j++){
                sb.append(tmp[j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static int[] bfs(int start) {
        Queue<Integer> q = new ArrayDeque<>();
        int visited[] = new int[n+1];
        Arrays.fill(visited, -1);

        visited[1] = 0;
        q.add(start);

        int depth = 0;
        while (!q.isEmpty()) {
            int size = q.size();

            depth++;
            for (int s = 0; s < size; s++) {
                int tmp = q.poll();

                for (int next : adjList[tmp]) {
                    if(visited[next] >= 0) continue;

                    visited[next] = depth;
                    q.add(next);
                }

            }
        }

        return visited;
    }
}
