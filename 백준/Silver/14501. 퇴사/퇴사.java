import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine().trim());

        int dp[] = new int[25];
        int T[] = new int[25];
        int P[] = new int[25];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int tmpT = Integer.parseInt(st.nextToken());
            int tmpP = Integer.parseInt(st.nextToken());

            T[i + 1] = tmpT;
            P[i + 1] = tmpP;
        }

        for (int i = 1; i <= N + 1; i++) {
            for (int j = i + T[i]; j <= N + 1; j++) {
                dp[j] = Math.max(dp[j], dp[i] + P[i]);
            }
//            dp[i + T[i]] = Math.max(dp[i] + P[i], dp[i + T[i]]);
        }
//        System.out.println(Arrays.toString(dp));
        System.out.println(dp[N + 1]);

    }

}