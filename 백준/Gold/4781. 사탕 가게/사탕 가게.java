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
            int m = (int) Math.round(mm * 100.0);

            if(n == 0) break;
            int dp[] = new int[m+1];

            int candies[][] = new int[n][2];
            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());

                int c = Integer.parseInt(st.nextToken());
                float pp = Float.parseFloat(st.nextToken());
                int p = (int) Math.round(pp * 100.0);

                candies[i][0] = c;
                candies[i][1] = p;
            }

            for(int i = 0; i < n; i++){

                int c = candies[i][0];
                int p = candies[i][1];

                for(int j = p; j <= m; j++){
                    dp[j] = Math.max(dp[j], dp[j - p] + c) ;
                }
            }

            sb.append(dp[m]).append("\n");



        }

        System.out.println(sb);

    }
}