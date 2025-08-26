import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while (true){
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            float mm = Float.parseFloat(st.nextToken());
            int m = (int) Math.round(mm * 100);

            if(n == 0) break;
            int dp[] = new int[m+1];

            int c[] = new int[n];
            int p[] = new int[n];
            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());

                c[i] = Integer.parseInt(st.nextToken());
                float pp = Float.parseFloat(st.nextToken());
                p[i] = (int) Math.round(pp * 100);
            }

            for(int i = 0; i < n; i++){

                int ccc = c[i];
                int ppp = p[i];

                for(int j = ppp; j <= m; j++){
                    dp[j] = Math.max(dp[j], dp[j - ppp] + ccc) ;
                }
            }

            sb.append(dp[m]).append("\n");
        }
        System.out.println(sb);
    }
}