import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int MOD = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine().trim());

        long memorized[][] = new long[N+1][10];

        for(int i = 1; i < 10; i++){
            memorized[1][i] = 1;
        }

        for(int i = 2; i <= N; i++){
            for(int j = 0; j < 10; j++){
                if(j == 0){
                    memorized[i][j] = memorized[i-1][1] % MOD;
                }
                else if(j == 9){
                    memorized[i][j] = memorized[i-1][8] % MOD;
                }
                else{
                    memorized[i][j] = (memorized[i-1][j-1] + memorized[i-1][j+1]) % MOD;
                }
            }
        }

        long ans = 0;

        for(int i = 0; i < 10; i++){
            ans = (ans + memorized[N][i]) % MOD;
        }

        System.out.println(ans);
    }
}