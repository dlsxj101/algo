import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine().trim());

        int line[][] = new int[N + 1][2];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            line[i][0] = Integer.parseInt(st.nextToken());
            line[i][1] = Integer.parseInt(st.nextToken());
        }

        int Q = Integer.parseInt(br.readLine().trim());

        A:
        for (int qq = 0; qq < Q; qq++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            Queue<Integer> q = new ArrayDeque<>();
            boolean visited[] = new boolean[N + 1];

            q.add(a);
            visited[a] = true;

            int depth = 0;
            while (!q.isEmpty()) {
                int size = q.size();

                depth++;
                for (int s = 0; s < size; s++) {
                    int now = q.poll();

                    for (int i = 1; i <= N; i++) {
                        if (visited[i]) continue;

                        if (line[i][1] < line[now][0]) continue;
                        if (line[i][0] > line[now][1]) continue;

                        if (i == b) {
                            sb.append(depth).append("\n");
                            continue A;
                        }

                        visited[i] = true;
                        q.add(i);
                    }
                }
            }
            sb.append(-1).append("\n");
        }
        System.out.println(sb);
    }
}