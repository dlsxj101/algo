import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine().trim());
        int k = Integer.parseInt(br.readLine().trim());

        int coins[] = new int[k];
        int counts[] = new int[k];

        for(int i = 0; i < k; i++){
            st = new StringTokenizer(br.readLine());

            coins[i] = Integer.parseInt(st.nextToken());
            counts[i] = Integer.parseInt(st.nextToken());
        }

        int dp[] = new int[T + 1];
        dp[0] = 1;

        for(int i = 0; i < k; i++){
            int coin = coins[i];
            int count = counts[i];

            for(int j = T; j >= 1; j--){
                for(int cnt = 1; cnt <= count; cnt++){
                    if(j - coin * cnt < 0) break;

                    dp[j] += dp[j - coin * cnt];
                }
            }
        }

        System.out.println(dp[T]);
    }
}