import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static long ans;
    static long cnt[];
    static List<Integer> adjList[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine().trim());

        cnt = new long[N + 1];

        adjList = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 2; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            String animalName = st.nextToken();
            int animalCnt = Integer.parseInt(st.nextToken());
            int connect = Integer.parseInt(st.nextToken()); // 부모

            // ★ 변경 포인트: 간선을 "부모 -> 자식"으로 저장
            adjList[connect].add(i);

            if (animalName.equals("W")) {
                cnt[i] = -animalCnt; // 늑대는 음수로
            } else {
                cnt[i] = animalCnt;  // 양은 양수로
            }
        }

        // 루트에서 한 번만 DFS
        ans = dfs(1);
        System.out.println(ans);
    }

    // ★ 후위 순회 DFS: 자식 결과를 모아서 현재 노드에서 처리
    static long dfs(int u) {
        long sum = 0;
        for (int v : adjList[u]) {
            sum += dfs(v);
        }

        if (cnt[u] >= 0) {          // S x
            return sum + cnt[u];
        } else {                    // W x
            long remain = sum + cnt[u]; // (cnt[u]는 음수라서 빼는 효과)
            return remain > 0 ? remain : 0;
        }
    }
}
