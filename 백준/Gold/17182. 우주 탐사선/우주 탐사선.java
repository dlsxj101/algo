import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, K, ans;
    static int dist[][];
    static boolean visited[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dist = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                dist[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int k = 0; k < N; k++){
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        ans = Integer.MAX_VALUE;
        visited = new boolean[N];
//        visited[K] = true;
        dfs(0, K, 0);

        System.out.println(ans);
    }
    static void dfs(int cnt, int now, int sum){
        if(cnt >= N){
            ans = Math.min(ans, sum);

            return;
        }

        for(int i = 0; i < N; i++){
            if(visited[i]) continue;

            visited[i] = true;
            dfs(cnt + 1, i, sum + dist[now][i]);
            visited[i] = false;
        }
    }
}