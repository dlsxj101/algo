import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int D[] = new int[N + 1];   //건물 완공에 걸리는 시간
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                D[i] = Integer.parseInt(st.nextToken());
            }

            List<Integer> adjList[] = new List[N + 1];
            for (int i = 1; i <= N; i++) {
                adjList[i] = new ArrayList<>();
            }

            int degree[] = new int[N + 1];
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());

                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());

                adjList[X].add(Y);
                degree[Y]++;
            }

            int W = Integer.parseInt(br.readLine().trim());

            int answer[] = new int[N + 1];

            Queue<Integer> q = new ArrayDeque<>();
            for (int i = 1; i <= N; i++) {
//                answer[i] = D[i];
                if (degree[i] != 0) continue;

                q.add(i);
                answer[i] = D[i];
            }

            while (!q.isEmpty()) {
                int cur = q.poll();

                for (int next : adjList[cur]) {
                    answer[next] = Math.max(answer[next], answer[cur] + D[next]);
                    degree[next]--;
                    if (degree[next] == 0) {
                        q.add(next);
                    }
                }
            }

            sb.append(answer[W]).append("\n");
        }
        System.out.println(sb);
    }
}