import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        StringTokenizer st;

        List<int[]> node = new ArrayList<>();
        node.add(new int[]{0, 0, 0});   //dummy
        int index = 1;
        A:
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int flag = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if (flag == 1) {
                node.add(new int[]{index, x, y});
                index++;
            }
            if (flag == 2) {
                boolean[] visited = new boolean[N + 1];
                Queue<Integer> q = new ArrayDeque<>();

                visited[x] = true;
                q.add(x);

                while (!q.isEmpty()) {
                    int cur = q.poll();
                    int[] nowArr = node.get(cur);

                    if (nowArr[0] == y) {
                        sb.append(1).append("\n");
                        continue A;
                    } else {
                        for (int[] next : node) {
                            if (next[0] == 0) continue;
                            if (visited[next[0]]) continue;
                            if ((next[1] < nowArr[1] && nowArr[1] < next[2]) || (next[1] < nowArr[2] && nowArr[2] < next[2])) {
                                visited[next[0]] = true;
                                q.add(next[0]);
                            }
                        }
                    }
                }
                sb.append(0).append("\n");
            }
        }
        System.out.println(sb);
    }
}