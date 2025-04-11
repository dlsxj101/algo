import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int combi[][];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            combi = new int[M + 1][N + 1];
            int ans = dfs(M, N);

            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

    private static int dfs(int n, int r) {
        if (combi[n][r] > 0) {
            return combi[n][r];
        }

        if (n == r || r == 0) {
            return 1;
        }

        return combi[n][r] = dfs(n - 1, r - 1) + dfs(n - 1, r);
    }
}