import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int V, parents[];
    static int[] degree;

    static void make() {
        parents = new int[V + 1];
        Arrays.fill(parents, -1);
        degree = new int[V + 1];
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

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        make();
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            union(a, b);
            // 간선을 읽으면서 각 정점의 차수 증가
            degree[a]++;
            degree[b]++;
        }

        // 간선이 하나라도 있는 정점들을 대상으로 연결성 확인
        int comp = -1;
        for (int i = 1; i <= V; i++) {
            if (degree[i] > 0) {
                if (comp == -1) {
                    comp = findSet(i);
                } else if (comp != findSet(i)) {
                    System.out.println("NO");
                    return;
                }
            }
        }

        // 홀수 차수를 가진 정점의 수 세기
        int oddCount = 0;
        for (int i = 1; i <= V; i++) {
            if (degree[i] % 2 == 1) {
                oddCount++;
            }
        }

        // 오일러 경로 조건: 홀수 차수의 정점이 0개 또는 2개여야 함
        if (oddCount == 0 || oddCount == 2)
            System.out.println("YES");
        else
            System.out.println("NO");
    }
}
