import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int MOD = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine().trim());

        long memorized[][] = new long[101][10];

        for(int i = 1; i < 10; i++){
            memorized[1][i] = 1;
        }

        long cnt[] = new long[101];
        cnt[1] = 1;
        for(int i = 2; i < 101; i++){
            for(int j = 0; j < 10; j++){
                if(j == 0){
                    memorized[i][j] = memorized[i-1][1] % MOD;
                    cnt[i] = (cnt[i] + memorized[i][j]) % MOD;
                }
                else if(j == 9){
                    memorized[i][j] = memorized[i-1][8] % MOD;
                    cnt[i] = (cnt[i] + memorized[i][j]) % MOD;
                }
                else{
                    memorized[i][j] = (memorized[i-1][j-1] + memorized[i-1][j+1]) % MOD;
                }
            }
        }

        long dp[] = new long[N + 1];

        dp[1] = 9;
        for(int i = 2; i <= N; i++){
            long two = (dp[i - 1] * 2) % MOD;        // 곱셈도 즉시 모듈러
            long val = (two - cnt[i - 1]) % MOD;     // 뺄셈 후 음수 가능
            if (val < 0) val += MOD;                 // 음수 보정
            dp[i] = val;
        }

        System.out.println(dp[N] % MOD);


    }
}